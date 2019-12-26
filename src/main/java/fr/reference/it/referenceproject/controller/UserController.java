package fr.reference.it.referenceproject.controller;


import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import fr.reference.it.referenceproject.domaine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/users")
    public List<Utilisateur> getUsers() {
        return userService.getAllUsers();
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
}
