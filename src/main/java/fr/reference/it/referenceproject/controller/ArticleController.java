package fr.reference.it.referenceproject.controller;


import fr.reference.it.referenceproject.domaine.dto.Article;
import fr.reference.it.referenceproject.domaine.dto.Response;
import fr.reference.it.referenceproject.domaine.service.ArticleService;
import fr.reference.it.referenceproject.domaine.service.FileService;
import fr.reference.it.referenceproject.security.jwt.model.JwtTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST})
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    public static final String DELETE_MESSAGE = "There is a problem with article ID ";
    public static final String PATH = "C:/store/";

    @Autowired
    ArticleService articleService;
    @Autowired
    private FileService fileService;
    @Autowired
    ServletContext context;
    @GetMapping("/")
    public List<Article> fetch_all_articles(){
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article fetch_article_byID(@PathVariable("id") Integer pId){
        try{
            return articleService.getArticleById(pId);
        }catch(NoSuchElementException e)
        {
            return null;
        }
    }

    @PostMapping("/")
    public ResponseEntity create_article(@RequestBody Article pArticle){
        int id;
        try{
            id = articleService.create_article(pArticle);
        }catch (Exception e)
        {
            return new ResponseEntity(DELETE_MESSAGE +pArticle.getId() ,HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(id,HttpStatus.ACCEPTED);
    }
    @PostMapping(value = "/upload",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity upload_article_image(@RequestParam("file") MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(PATH + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity("Technical problem when you try to upload the file" ,HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(new Response(fileName));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer pId){
        try{
            articleService.delete_article(pId);
        }catch (Exception e)
        {
            return new ResponseEntity(DELETE_MESSAGE +pId ,HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(new Response(String.format("The Product with id %d is deleted",pId)),HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateArticle(@RequestBody Article pArticle){
        try{
            articleService.update_article(pArticle);
        }catch (Exception e)
        {
            return new ResponseEntity(DELETE_MESSAGE +pArticle.getId() ,HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(new Response(String.format("The Product %s is updated",pArticle.getName())),HttpStatus.OK);
    }
    @PostMapping(value = "/test")
    public void handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.uploadFile(file);
    }
}
