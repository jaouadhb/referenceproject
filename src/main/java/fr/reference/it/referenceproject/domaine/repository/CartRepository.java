package fr.reference.it.referenceproject.domaine.repository;


import fr.reference.it.referenceproject.dataacces.JpaCart;
import fr.reference.it.referenceproject.dataacces.entity.CartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CartRepository {

    @Autowired
    JpaCart jpaCart;

    public CartEntity storeCart(CartEntity pCart) {
        return jpaCart.save(pCart);
    }

    public CartEntity getCartByUserId(Integer pUserId) {
        return jpaCart.getCartByUserId(pUserId);
    }

    public Optional<CartEntity> getCartById(Integer pCartID) {
        return jpaCart.findById(pCartID);
    }
}
