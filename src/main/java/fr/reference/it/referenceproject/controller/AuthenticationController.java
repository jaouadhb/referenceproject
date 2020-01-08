package fr.reference.it.referenceproject.controller;


import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import fr.reference.it.referenceproject.domaine.service.UserService;
import fr.reference.it.referenceproject.security.jwt.config.JwtTokenUtil;
import fr.reference.it.referenceproject.security.jwt.exception.AuthenticationException;
import fr.reference.it.referenceproject.security.jwt.model.JwtTokenRequest;
import fr.reference.it.referenceproject.security.jwt.model.JwtTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

@RestController
public class AuthenticationController {

    @Value("${jwt.http-request-header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserService userService;
    @Autowired
    private UserDetailsService jwtUserDetailsService;
    private String username;

    @PostMapping(value = "${jwt.get-token-uri}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtTokenRequest authenticationRequest)
            throws AuthenticationException {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        JwtTokenResponse jwtTokenResponse = new JwtTokenResponse(token);
        mapper(jwtTokenResponse,userService.findUserByUserName(authenticationRequest.getUsername()));
        return ResponseEntity.ok(jwtTokenResponse);
    }
    @PostMapping(value = "${jwt.sign-in}")
    public ResponseEntity<?> signIn(@RequestBody Utilisateur user) {

        Optional<Utilisateur> userInDataBase = userService.findUserByUserName(user.getUsername());
        if(userInDataBase.isPresent())
        {
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        }
        userService.addUser(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    private void mapper(JwtTokenResponse jwtTokenResponse, Optional<Utilisateur> userByUserName) {
        Utilisateur utilisateur = userByUserName.get();
        jwtTokenResponse.setEmail(utilisateur.getEmail());
        jwtTokenResponse.setDateNaissance(utilisateur.getDateNaissance());
        jwtTokenResponse.setUsername(utilisateur.getUsername());
        jwtTokenResponse.setFirstName(utilisateur.getPrenom());
        jwtTokenResponse.setLastName(utilisateur.getNom());
        jwtTokenResponse.setRole(utilisateur.getRole());
    }

    @PostMapping(value = "${jwt.refresh-token-uri}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);

        if (jwtTokenUtil.canTokenBeRefreshed(token)) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtTokenResponse(token));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }
    }
}
