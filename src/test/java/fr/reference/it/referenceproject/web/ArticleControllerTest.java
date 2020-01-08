package fr.reference.it.referenceproject.web;

import fr.reference.it.referenceproject.config.SqlReference;
import fr.reference.it.referenceproject.controller.ArticleController;
import fr.reference.it.referenceproject.controller.UserController;
import fr.reference.it.referenceproject.dataacces.JpaArticle;
import fr.reference.it.referenceproject.domaine.dto.Article;
import fr.reference.it.referenceproject.domaine.repository.ArticleRepository;
import fr.reference.it.referenceproject.domaine.service.ArticleService;
import fr.reference.it.referenceproject.domaine.service.UserService;
import fr.reference.it.referenceproject.security.jwt.config.JwtTokenUtil;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = { ArticleController.class, JwtTokenUtil.class})
class ArticleControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    ArticleController articleController;
    @MockBean
    ArticleService articleService;
    String token;

    @Test
    void contextLoads() {
    }
    @BeforeEach
    public void setUpFields() {
        token = "Bearer " + jwtTokenUtil.generateToken(new User("jaouad", "jaouad", Collections.EMPTY_LIST));
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }
    @Test
    void should_Fetch_all_articles() throws Exception{
        when(articleService.getAllArticles()).thenReturn(Arrays.asList(new Article(1,"Asus",1453.2,"description","img.png")));
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/articles/")).andDo(MockMvcResultHandlers.print()).andReturn().getResponse();
        Assert.assertEquals(200,response.getStatus());
    }

    @Test
    void should_Fetch_article_byID() throws Exception{
        when(articleService.getArticleById(1)).thenReturn(new Article(1,"Asus",1453.2,"description","img.png"));
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/articles/1")).andDo(MockMvcResultHandlers.print()).andReturn().getResponse();
        Assert.assertEquals(200,response.getStatus());
    }

    @Test
    void should_DeleteById() throws Exception{
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/articles/1")).andDo(MockMvcResultHandlers.print()).andReturn().getResponse();
        Assert.assertEquals(202,response.getStatus());
        verify(articleService).delete_article(anyInt());
    }

    @Test
    void should_throw_exception_when_given_id5() throws Exception{
        Mockito.doThrow(RuntimeException.class).when(articleService).delete_article(1);
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/articles/1")).andDo(MockMvcResultHandlers.print()).andReturn().getResponse();
        Assert.assertEquals(406,response.getStatus());
    }

}
