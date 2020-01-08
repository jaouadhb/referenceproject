package fr.reference.it.referenceproject.controller;


import fr.reference.it.referenceproject.domaine.dto.Article;
import fr.reference.it.referenceproject.domaine.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    public static final String DELETE_MESSAGE = "There is a problem with article ID ";

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public List<Article> fetch_all_articles(){
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article fetch_article_byID(@PathVariable("id") Integer pId){
        return articleService.getArticleById(pId);
    }

    @PostMapping("/")
    public ResponseEntity create_article(@RequestBody Article pArticle){
        try{
            articleService.create_article(pArticle);
        }catch (Exception e)
        {
            return new ResponseEntity(DELETE_MESSAGE +pArticle.getId() ,HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer pId){
        try{
            articleService.delete_article(pId);
        }catch (Exception e)
        {
            return new ResponseEntity(DELETE_MESSAGE +pId ,HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
    @PutMapping("/{id}")
    public ResponseEntity updateArticle(@RequestBody Article pArticle){
        try{
            articleService.update_article(pArticle);
        }catch (Exception e)
        {
            return new ResponseEntity(DELETE_MESSAGE +pArticle.getId() ,HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
