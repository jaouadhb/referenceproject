package fr.reference.it.referenceproject.web;

import fr.reference.it.referenceproject.controller.UserController;
import fr.reference.it.referenceproject.dataacces.JpaUserDAO;
import fr.reference.it.referenceproject.dataacces.PersoneRepository;
import fr.reference.it.referenceproject.domaine.dto.Person;
import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import fr.reference.it.referenceproject.domaine.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
class ApplicationAngularBackTests {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    @MockBean
    PersoneRepository personeRepository;
 /*   @Autowired
    JwtTokenUtil jwtTokenUtil;*/
    @MockBean
    JpaUserDAO jpaUserDAO;
    String token;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setUpFields() {
       // token = "Bearer " + jwtTokenUtil.generateToken(new User("jaouad", "jaouad", Collections.EMPTY_LIST));
    }


    @Test
        void getAllUsersTest() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(new Utilisateur("jaouad@gmail.com", "jaouad", "hba", new Date())));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void shold_Get_User_With_GivenId() throws Exception {
        when(personeRepository.findByUsername(any())).thenReturn(Optional.of(new Person("jaouad", "jaouad")));
        when(userService.findUserById(1)).thenReturn(Optional.of(new Utilisateur("jaouad@gmail.com", "jaouad", "hba", new Date())));

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("api/users/1")
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        assertEquals(200, response.getStatus());
        verify(userService).findUserById(1);
    }

    @Test
    void shold_Delete_User_With_GiverId() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("api/users/user/1")).andDo(MockMvcResultHandlers.print()).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        verify(userService).deleteUserById(1);
    }
}
