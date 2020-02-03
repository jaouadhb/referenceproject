package fr.reference.it.referenceproject.domaine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.reference.it.referenceproject.dataacces.entity.ArticleInformation;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Integer id;
    @JsonProperty("articleInformations")
    private List<ArticleInformation> articleInformations;

    public Cart() {
        articleInformations= new ArrayList<>();
    }
    public Cart(List<ArticleInformation> articleInformations, Integer id) {
        this.articleInformations = articleInformations;
        this.id = id;
    }

    public List<ArticleInformation> getArticleInformations() {
        return articleInformations;
    }

   public void updateArticleInformations(List<ArticleInformation> articleInformations) {
       this.articleInformations.clear();
       List<ArticleInformation> lArticleInformations = new ArrayList<>();
       articleInformations.stream().forEach(articleInformation -> {
           ArticleInformation lArticleInformation = new ArticleInformation();
           if(articleInformation.getArticle().getImage().split("/").length>3)
           {
               articleInformation.getArticle().setImage(articleInformation.getArticle().getImage().split("/")[3]);
               lArticleInformation.setArticle(articleInformation.getArticle());
               lArticleInformation.setAmount(articleInformation.getAmount());
               lArticleInformation.setId(articleInformation.getId());
               lArticleInformations.add(lArticleInformation);
           }

       });
       this.articleInformations.addAll(lArticleInformations);
    }

    public void setArticleInformations(List<ArticleInformation> articleInformations) {
        this.articleInformations = articleInformations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
