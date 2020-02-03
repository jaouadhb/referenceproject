package fr.reference.it.referenceproject.domaine.service;

import fr.reference.it.referenceproject.dataacces.entity.ArticleEntity;
import fr.reference.it.referenceproject.dataacces.mapper.Mapper;
import fr.reference.it.referenceproject.domaine.dto.Article;
import fr.reference.it.referenceproject.domaine.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    @Qualifier("articleMapper")
    Mapper<ArticleEntity, Article> articleMapper;

    public List<Article> getAllArticles(){
        return articleRepository.findAll().stream().map(articleMapper::map).collect(Collectors.toList());
    }

    public Article getArticleById(Integer pId){
        return articleRepository.findById(pId).map(articleMapper::map).get();
    }

    public void update_article(Article pArticle){
        ArticleEntity articleEntity = articleMapper.inverseMap(pArticle);
        articleRepository.updateArticle(articleEntity);
    }
    public int create_article(Article pArticle){
        ArticleEntity articleEntity = articleMapper.inverseMap(pArticle);
        return articleRepository.createArticle(articleEntity);
    }
    public void delete_article(Integer pId){
        articleRepository.deleteById(pId);
    }
}
