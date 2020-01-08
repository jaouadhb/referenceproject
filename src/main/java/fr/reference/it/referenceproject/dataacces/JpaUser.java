package fr.reference.it.referenceproject.dataacces;

import fr.reference.it.referenceproject.dataacces.entity.UserEntity;
import fr.reference.it.referenceproject.domaine.dto.Person;
import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUser extends JpaRepository<UserEntity,Integer>{
    @Query(value = "select * from utilisateurs  where utilisateurs.username = :username",nativeQuery = true)
    List<UserEntity> findByUsername(@Param("username") String username);
}
