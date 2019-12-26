package fr.reference.it.referenceproject.dataacces;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;

@SpringBootTest
public class JpaUserDaoIT {
    @Autowired
    JpaUserDAO jpaUserDAO;
    @Test
    void should_return_somthing_from_db() {
        jpaUserDAO.findAll();
        Assertions.assertThat(jpaUserDAO.findAll()).hasSize(1);
    }
    @Test()
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,scripts = "classpath:db.sql")
    void should_delete_the_user() {
        jpaUserDAO.deleteById(1);
        Assertions.assertThat(jpaUserDAO.findAll()).hasSize(0);
    }

    @Test
    void withNewUserData_UpdateUser_FromDB() {
        UserEntity userEntity = new UserEntity();
        userEntity.setDateNaissance(new Date());
        userEntity.setEmail("hba@gmail.com");
        userEntity.setNom("hba");
        userEntity.setPrenom("jaouad");
        UserEntity other = jpaUserDAO.findAll().get(0);
        Assertions.assertThat(jpaUserDAO.save(userEntity)).isEqualToComparingOnlyGivenFields(other);
    }
}
