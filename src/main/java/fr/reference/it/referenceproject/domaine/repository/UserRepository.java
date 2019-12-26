package fr.reference.it.referenceproject.domaine.repository;



import fr.reference.it.referenceproject.domaine.dto.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UserRepository /*extends JpaRepository<Utilisateur,Integer>*/ {

    List<Utilisateur> findAll();

    Optional<Utilisateur> findUserById(int pId);

    void deleteUser(int pId);

    void updateUser(Utilisateur pUtilisateur);

    void saveUser(Utilisateur pUtilisateur);

}
