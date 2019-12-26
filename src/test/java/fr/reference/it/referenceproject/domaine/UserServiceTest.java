package fr.reference.it.referenceproject.domaine;


import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import fr.reference.it.referenceproject.domaine.repository.UserRepository;
import fr.reference.it.referenceproject.domaine.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository utilisateurRepositoryMock;

    UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(utilisateurRepositoryMock);
    }

    @Test
    void getAllUsers() {
        assertThat(userService.getAllUsers()).isEmpty();
    }

    @Test
    void should_return_same_list_as_repository() {
        BDDMockito.given(utilisateurRepositoryMock.findAll()).willReturn(Arrays.asList(new Utilisateur("jaouad@gmail.com","jaouad","hba",new Date())));
        assertThat(userService.getAllUsers()).hasSize(1);
    }

}
