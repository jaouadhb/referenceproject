package fr.reference.it.referenceproject.domaine.repository;

import fr.reference.it.referenceproject.config.SqlReference;
import fr.reference.it.referenceproject.dataacces.JpaArticle;
import fr.reference.it.referenceproject.dataacces.entity.ArticleEntity;
import fr.reference.it.referenceproject.dataacces.mapper.ArticleMapperImpl;
import fr.reference.it.referenceproject.dataacces.mapper.Mapper;
import fr.reference.it.referenceproject.domaine.dto.Article;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.AfterTestExecution;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ArticleRepositoryTest {
    @Autowired
    ArticleRepository articleRepository;
    Mapper<ArticleEntity, Article> mapper;
    @Autowired
    JpaArticle jpaArticle;

    @Test
    @SqlReference
    void should_FindAll() {
        Assert.assertEquals(1,articleRepository.findAll().size());
    }

    @Test
    @SqlReference
    void return_article_when_given_id_1(){
        ArticleEntity article = articleRepository.findById(1).get();
        Assert.assertEquals("Asus S550", article.getName());
        Assert.assertEquals(1110.20, article.getPrice(),2);
        Assert.assertEquals("This is Assus pc", article.getDescription().trim());
    }

    @Test
    @SqlReference
    void should_delete_article_with_id_1(){
        Assert.assertEquals(1,articleRepository.findAll().size());
        articleRepository.deleteById(1);
        Assert.assertEquals(0,articleRepository.findAll().size());
    }

    @Test
    @SqlReference
    void should_update_article() {

        ArticleEntity article = articleRepository.findById(1).get();
        article.setName("This is New Name");
        article.setPrice(10452);
        articleRepository.updateArticle(article);

        article = articleRepository.findById(1).get();

        Assert.assertEquals("This is New Name", article.getName());
        Assert.assertEquals(10452, article.getPrice(),2);
        Assert.assertEquals("This is Assus pc", article.getDescription().trim());
    }

    @AfterEach
    public void clean()
    {
        jpaArticle.deleteAll();
    }

}
