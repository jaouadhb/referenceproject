package fr.reference.it.referenceproject.security.repository;

import fr.reference.it.referenceproject.domaine.dto.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String username);
}
