package fr.reference.it.referenceproject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import fr.reference.it.referenceproject.domaine.service.UserService;
import fr.reference.it.referenceproject.security.jwt.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    public static final String OLD_PASSWORD = "oldPassword";
    public static final String NEW_PASSWORD = "newPassword";
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/users")
    public List<Utilisateur> getUsers() {
        return userService.getAllUsers().stream().filter(user->!"admin".equals(user.getRole())).collect(Collectors.toList());
    }

    @PostMapping("/users/information")
    public Utilisateur getCurrentUser(@RequestBody String userName){
        return userService.findUserByUserName(userName).get();
    }

    @GetMapping("/users/{id}")
    public Utilisateur getUser(@PathVariable int id) {
        return userService.findUserById(id).orElse(new Utilisateur());
    }

    @PostMapping("/users/user")
    public void addUser(@RequestBody Utilisateur user) {
        userService.addUser(user);
    }

    @DeleteMapping("/users/user/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }

    @PutMapping("users/user")
    public void editUser(@RequestBody Utilisateur user) {
        userService.updateUser(user);
    }

    @PutMapping("users/user/editpassword")
    public ResponseEntity<Object> editUserPassword(@RequestBody String passwordBody, @RequestHeader("Authorization") String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(passwordBody);
        String oldPassword = actualObj.get(OLD_PASSWORD).asText();
        String newPassword = actualObj.get(NEW_PASSWORD).asText();

        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        Optional<Utilisateur> userByUserName = userService.findUserByUserName(usernameFromToken);
        boolean isChanges = userService.updateUserPassword(userByUserName.get(),oldPassword,newPassword);

        if(isChanges)
        {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
