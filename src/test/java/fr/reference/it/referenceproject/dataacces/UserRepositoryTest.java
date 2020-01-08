package fr.reference.it.referenceproject.dataacces;

import fr.reference.it.referenceproject.dataacces.entity.UserEntity;
import fr.reference.it.referenceproject.domaine.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository utilisateurRepository;
    @Mock
    JpaUser jpaUserDAO;

    @BeforeEach
    void setUp() {
        //utilisateurRepository = new UserRepositoryImpl(jpaUserDAO, UserMapper.INSTANCE);
    }

    @Test
    void should_FindAll() {
        BDDMockito.given(jpaUserDAO.findAll()).willReturn(Arrays.asList(new UserEntity("jaouad@gmail.com","jaouad","hba",new Date())));
        assertThat(utilisateurRepository.findAll()).hasSize(10);
    }
}
