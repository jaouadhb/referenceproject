package fr.reference.it.referenceproject.domaine.service;
import fr.reference.it.referenceproject.dataacces.entity.ArticleInformation;
import fr.reference.it.referenceproject.dataacces.entity.CartEntity;
import fr.reference.it.referenceproject.dataacces.mapper.Mapper;
import fr.reference.it.referenceproject.dataacces.mapper.UserMapperImpl;
import fr.reference.it.referenceproject.domaine.dto.Cart;
import fr.reference.it.referenceproject.domaine.repository.CartRepository;
import fr.reference.it.referenceproject.domaine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    Mapper<CartEntity,Cart> cartMapper;
    public Cart saveCart(Cart pCart,int userId) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(pCart.getId());
        cartEntity.getArticleInformations().addAll(pCart.getArticleInformations());
        if(userRepository.findUserById(userId).isPresent()){
            cartEntity.setUserEntity(new UserMapperImpl().inverseMap(userRepository.findUserById(userId).get()));
        }
        Cart cart = cartMapper.map(cartRepository.storeCart(cartEntity));
        return cart;
    }

    public Cart fetch_cart_by_id(Integer pUserId) {
        return cartMapper.map(cartRepository.getCartByUserId(pUserId));
    }


    public Cart updateCart(Cart lCart) {
        CartEntity cartEntity = cartRepository.getCartById(lCart.getId()).get();
        List<ArticleInformation> lArticleInformations = new ArrayList<>();
        lCart.getArticleInformations().stream().forEach(articleInformation -> {
            ArticleInformation lArticleInformation = new ArticleInformation();
                lArticleInformation.setArticle(articleInformation.getArticle());
                lArticleInformation.setAmount(articleInformation.getAmount());
                lArticleInformation.setId(articleInformation.getId());
                lArticleInformations.add(lArticleInformation);
        });
        cartEntity.getArticleInformations().clear();

        cartEntity.getArticleInformations().addAll(lArticleInformations);

        Cart cart = cartMapper.map(cartRepository.storeCart(cartEntity));
        return cart;
    }
}
