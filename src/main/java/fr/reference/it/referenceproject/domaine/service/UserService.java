package fr.reference.it.referenceproject.domaine.service;

import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import fr.reference.it.referenceproject.domaine.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

      public void addUser(Utilisateur pUser) {
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
}
