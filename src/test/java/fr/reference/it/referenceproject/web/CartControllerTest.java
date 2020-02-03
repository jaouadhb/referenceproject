package fr.reference.it.referenceproject.web;

import fr.reference.it.referenceproject.controller.ArticleController;
import fr.reference.it.referenceproject.controller.CartController;
import fr.reference.it.referenceproject.domaine.dto.Cart;
import fr.reference.it.referenceproject.domaine.service.CartService;
import fr.reference.it.referenceproject.domaine.service.UserService;
import fr.reference.it.referenceproject.security.jwt.config.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resources;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = { CartController.class, JwtTokenUtil.class})
class CartControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @MockBean
    CartService cartService;
    @Autowired
    ArticleController articleController;
    private String token;

    @BeforeEach
    public void setUpFields() {
        token = "Bearer " + jwtTokenUtil.generateToken(new User("jaouad", "jaouad", Collections.EMPTY_LIST));
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }
    @Test
    void should_GetCartForCurrentUser() throws Exception {
        when(cartService.fetch_cart_by_id(anyInt())).thenReturn(new Cart());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cart")).andDo(MockMvcResultHandlers.print());
    }
}
