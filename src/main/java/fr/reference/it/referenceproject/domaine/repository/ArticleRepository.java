package fr.reference.it.referenceproject.domaine.repository;


import fr.reference.it.referenceproject.dataacces.JpaArticle;
import fr.reference.it.referenceproject.dataacces.entity.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArticleRepository {

    @Autowired
    JpaArticle jpaArticle;

    public List<ArticleEntity> findAll()
    {
        return jpaArticle.findAll();
    }

    public Optional<ArticleEntity> findById(Integer pId)
    {
        return jpaArticle.findById(pId);
    }

    public void deleteById(Integer pId)
    {
        jpaArticle.deleteById(pId);
    }
    public void updateArticle(ArticleEntity pArticleEntity)
    {
        jpaArticle.save(pArticleEntity);
    }
    public void createArticle(ArticleEntity pArticleEntity)
    {
        jpaArticle.save(pArticleEntity);
    }
    public void setJpaArticle(JpaArticle jpaArticle) {
        this.jpaArticle = jpaArticle;
    }
}
