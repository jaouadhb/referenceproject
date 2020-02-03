package fr.reference.it.referenceproject.dataacces;

import fr.reference.it.referenceproject.dataacces.entity.CartEntity;
import fr.reference.it.referenceproject.domaine.dto.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCart extends JpaRepository<CartEntity,Integer> {
    @Query(value = "select * from cart where cart.user_entity_id= :userId",nativeQuery = true)
    public CartEntity getCartByUserId(@Param("userId") Integer pUserId);
}
