package fr.reference.it.referenceproject.dataacces.mapper;

import fr.reference.it.referenceproject.dataacces.entity.ArticleEntity;
import fr.reference.it.referenceproject.dataacces.entity.UserEntity;
import fr.reference.it.referenceproject.domaine.dto.Article;
import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2019-11-13T21:41:58+0100",
        comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_191 (Oracle Corporation)"
)
@Component("articleMapper")
public class ArticleMapperImpl implements Mapper<ArticleEntity,Article> {
    @Value("${app.hosted.image.server.name}")
    String serverName;
    @Value("${app.hosted.image.server.port}")
    String serverPort;

    @Override
    public Article map(ArticleEntity pArticleEntity) {
        if(pArticleEntity==null){
            return null;
        }
        Article article = new Article();
        article.setId(pArticleEntity.getId());
        article.setDescription(pArticleEntity.getDescription());
        article.setName(pArticleEntity.getName());
        article.setPrice(pArticleEntity.getPrice());
        article.setImage(serverName+serverPort+pArticleEntity.getImage());
        return article;
    }

    @Override
    public ArticleEntity inverseMap(Article pArticle) {
        if(pArticle==null){
            return null;
        }
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setDescription(pArticle.getDescription());
        articleEntity.setName(pArticle.getName());
        articleEntity.setPrice(pArticle.getPrice());
        articleEntity.setImage(pArticle.getImage());
        if(pArticle.getId()!=null){
            articleEntity.setId(pArticle.getId());
        }
        return articleEntity;
    }
}
