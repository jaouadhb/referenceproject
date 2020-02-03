package fr.reference.it.referenceproject.domaine.repository;

import fr.reference.it.referenceproject.dataacces.JpaCart;
import fr.reference.it.referenceproject.dataacces.JpaUser;
import fr.reference.it.referenceproject.dataacces.entity.CartEntity;
import fr.reference.it.referenceproject.dataacces.entity.UserEntity;
import fr.reference.it.referenceproject.dataacces.mapper.Mapper;
import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CartRepositoryTest {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    @Qualifier("userMapper")
    private Mapper<UserEntity,Utilisateur> userMapper;
    @Autowired
    private JpaCart jpaCart;
    @Autowired
    private JpaUser jpaUser;


    @AfterEach
    void tearDown() {
    }

    @Test
    @Sql(scripts = "classpath:scripts/dataCart.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void should_StoreCart() {
        UserEntity utilisteur = userRepository
                .findUserById(102)
                .map(userMapper::inverseMap)
                .get();
        CartEntity cart = new CartEntity();
        cart.setUserEntity(utilisteur);
        CartEntity savedCart = cartRepository.storeCart(cart);
        assertNotNull(savedCart);
        assertEquals(utilisteur,savedCart.getUserEntity());
    }

    @Test
    @Sql(scripts = "classpath:scripts/saveCart.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void should_GetCartByUserId() {
        CartEntity cart = cartRepository.getCartByUserId(104);
        assertEquals("hba",cart.getUserEntity().getNom());
        assertEquals("jaouad",cart.getUserEntity().getPrenom());
        assertEquals("jaouadhba@gmail.com",cart.getUserEntity().getEmail());
    }

    @AfterEach
    public void clean() {
        jpaCart.deleteAll();
        jpaUser.deleteAll();
    }
}
