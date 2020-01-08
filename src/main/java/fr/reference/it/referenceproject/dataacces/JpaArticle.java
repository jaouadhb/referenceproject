package fr.reference.it.referenceproject.dataacces;

import fr.reference.it.referenceproject.dataacces.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaArticle extends JpaRepository<ArticleEntity,Integer> {
}
