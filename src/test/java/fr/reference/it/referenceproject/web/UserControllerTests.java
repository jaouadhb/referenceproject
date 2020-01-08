package fr.reference.it.referenceproject.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.reference.it.referenceproject.controller.UserController;
import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import fr.reference.it.referenceproject.domaine.service.UserService;
import fr.reference.it.referenceproject.security.jwt.config.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = {UserController.class, JwtTokenUtil.class})
class UserControllerTests {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserController userController;
    String token;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setUpFields() {
        token = "Bearer " + jwtTokenUtil.generateToken(new User("jaouad", "jaouad", Collections.EMPTY_LIST));
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    void getAllUsersTest() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(new Utilisateur("jaouad@gmail.com", "jaouad", "hba", new Date())));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")).andDo(MockMvcResultHandlers.print());
    }

    @Test
    void shold_Get_User_With_GivenId() throws Exception {
        when(userService.findUserById(1)).thenReturn(Optional.of(new Utilisateur("jaouad@gmail.com", "hba", "jaouad", new Date())));

        MockHttpServletResponse response = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/users/1")
                        .header("Authorization", token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse();

        assertEquals(200, response.getStatus());
        ObjectMapper objectMapper = new ObjectMapper();
        Utilisateur utilisateurs = objectMapper.readValue(response.getContentAsString(), Utilisateur.class);
        assertEquals("jaouad", utilisateurs.getPrenom());
        assertEquals("hba", utilisateurs.getNom());

    }

    @Test
    void shold_Delete_User_With_GiverId() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/user/1")).andDo(MockMvcResultHandlers.print()).andReturn().getResponse();
        assertEquals(200, response.getStatus());
        verify(userService).deleteUserById(1);
    }

}
