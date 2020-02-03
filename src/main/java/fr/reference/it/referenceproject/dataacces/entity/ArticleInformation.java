package fr.reference.it.referenceproject.dataacces.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ArticleInformation implements Serializable {

    private static final long serialVersionUID = -56161768970310834L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private ArticleEntity article;
    private Integer amount;

    public ArticleInformation() {
    }

    public ArticleInformation(Integer id, ArticleEntity article, Integer amount) {
        this.id = id;
        this.article = article;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
