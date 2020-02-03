package fr.reference.it.referenceproject.dataacces.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table(name = "cart")
@Entity(name = "cart")
public class CartEntity implements Serializable {

    private static final long serialVersionUID = -5616176897031140834L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ArticleInformation> articleInformations;

    @ManyToOne
    private UserEntity userEntity;

    public CartEntity() {
        articleInformations = new ArrayList<>();
    }

    public CartEntity(List<ArticleInformation> articleInformations, UserEntity userEntity) {
        this.articleInformations = articleInformations;
        this.userEntity = userEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ArticleInformation> getArticleInformations() {
        return articleInformations;
    }

    public void setArticleInformations(List<ArticleInformation> articleInformations) {
        this.articleInformations = articleInformations;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
