package fr.reference.it.referenceproject.domaine.service;

import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import fr.reference.it.referenceproject.domaine.repository.UserRepository;
import fr.reference.it.referenceproject.security.jwt.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

      public void addUser(Utilisateur pUser) {
          if(!StringUtils.isEmpty(pUser.getPassword())){
              pUser.setPassword(passwordEncoder.encode(pUser.getPassword()));
          }
          userRepository.saveUser(pUser);
    }


     public void deleteUserById(int pUser) {
        userRepository.deleteUser(pUser);
    }


    public void updateUser(Utilisateur pUser) {
        userRepository.updateUser(pUser);
    }

    public List<Utilisateur> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<Utilisateur> findUserById(Integer pId) {
        return userRepository.findUserById(pId);
    }

    public Optional<Utilisateur> findUserByUserName(String userName)
    {
        return userRepository.findByUsername(userName);
    }
    public boolean updateUserPassword(Utilisateur user ,String oldPassword,String newPassword) {
        if(passwordEncoder.matches(oldPassword, user.getPassword())){
            user.setPassword(passwordEncoder.encode(newPassword));
            updateUser(user);
            return true;
        }
        return false;
    }
}
