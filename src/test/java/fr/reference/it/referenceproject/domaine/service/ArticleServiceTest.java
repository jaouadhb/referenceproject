package fr.reference.it.referenceproject.domaine.service;

import fr.reference.it.referenceproject.config.SqlReference;
import fr.reference.it.referenceproject.dataacces.JpaArticle;
import fr.reference.it.referenceproject.dataacces.entity.ArticleEntity;
import fr.reference.it.referenceproject.domaine.dto.Article;
import fr.reference.it.referenceproject.domaine.repository.ArticleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;
    @Autowired
    JpaArticle jpaArticle;
    @Test
    @SqlReference
    void should_GetAllArticles() {
        Assert.assertEquals(1,articleService.getAllArticles().size());
    }

    @Test
    @SqlReference
    void should_GetArticleById() {
        Article article = articleService.getArticleById(1);
        Assert.assertEquals("Asus S550", article.getName());
        Assert.assertEquals(1110.20, article.getPrice(),2);
        Assert.assertEquals("This is Assus pc", article.getDescription().trim());
    }

    @Test
    void should_Create_article() {
        Article article = new Article();
        article.setName("Asus pc");
        article.setImage("img.png");
        article.setPrice(1001.5);

        articleService.create_article(article);
        Assert.assertEquals(1,articleService.getAllArticles().size());
    }

    @Test
    @SqlReference
    void should_Delete_article() {
        Assert.assertEquals(1,articleService.getAllArticles().size());
        articleService.delete_article(1);
        Assert.assertEquals(0,articleService.getAllArticles().size());
    }
    @AfterEach
    public void clean()
    {
        jpaArticle.deleteAll();
    }
}
