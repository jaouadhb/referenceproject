package fr.reference.it.referenceproject.dataacces.mapper;

import fr.reference.it.referenceproject.dataacces.entity.CartEntity;
import fr.reference.it.referenceproject.domaine.dto.Cart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Component
public class CartMapper implements Mapper<CartEntity, Cart> {

    @Override
    public Cart map(CartEntity userEntity) {
        Cart cart = null;
        if(userEntity != null){
            cart = new Cart();
            cart.getArticleInformations().addAll(userEntity.getArticleInformations());
            cart.setId(userEntity.getId());
        }
        return cart;
    }

    @Override
    public CartEntity inverseMap(Cart utilisateur) {
        return null;
    }
}
