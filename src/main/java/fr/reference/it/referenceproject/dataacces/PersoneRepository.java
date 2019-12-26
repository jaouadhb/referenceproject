package fr.reference.it.referenceproject.dataacces;

import fr.reference.it.referenceproject.domaine.dto.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersoneRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String username);
}
