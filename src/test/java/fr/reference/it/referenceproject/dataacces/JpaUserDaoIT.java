package fr.reference.it.referenceproject.dataacces;

import fr.reference.it.referenceproject.dataacces.entity.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;

@SpringBootTest
public class JpaUserDaoIT {
    @Autowired
    JpaUser jpaUser;
    @Test
    void should_return_somthing_from_db() {
        jpaUser.findAll();
        Assertions.assertThat(jpaUser.findAll()).hasSize(1);
    }
    @Test()
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,scripts = "classpath:db.sql")
    void should_delete_the_user() {
        jpaUser.deleteById(1);
        Assertions.assertThat(jpaUser.findAll()).hasSize(0);
    }

    @Test
    void withNewUserData_UpdateUser_FromDB() {
        UserEntity userEntity = new UserEntity();
        userEntity.setDateNaissance(new Date());
        userEntity.setEmail("hba@gmail.com");
        userEntity.setNom("hba");
        userEntity.setPrenom("jaouad");
        UserEntity other = jpaUser.findAll().get(0);
        Assertions.assertThat(jpaUser.save(userEntity)).isEqualToComparingOnlyGivenFields(other);
    }
}
