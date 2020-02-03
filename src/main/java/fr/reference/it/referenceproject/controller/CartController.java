package fr.reference.it.referenceproject.controller;


import fr.reference.it.referenceproject.domaine.dto.Cart;
import fr.reference.it.referenceproject.domaine.dto.Utilisateur;
import fr.reference.it.referenceproject.domaine.service.CartService;
import fr.reference.it.referenceproject.domaine.service.UserService;
import fr.reference.it.referenceproject.security.jwt.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,methods = {RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.OPTIONS,RequestMethod.POST})
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CartService cartService;

    @Value("${app.hosted.image.server.name}")
    String serverName;
    @Value("${app.hosted.image.server.port}")
    String serverPort;

    @GetMapping
    public Cart getCartForCurrentUser(@RequestHeader("Authorization") String token){
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        Optional<Utilisateur> userByUserName = userService.findUserByUserName(usernameFromToken);
        Cart cart = cartService.fetch_cart_by_id(userByUserName.get().getId());

        cart.getArticleInformations().stream().forEach(articleInformation -> {
            articleInformation.getArticle().setImage(serverName+serverPort+articleInformation.getArticle().getImage());
        });

        return cart;
    }

    @PostMapping
    public Cart addToCart(@RequestBody Cart cart, @RequestHeader("Authorization") String token) {
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        Optional<Utilisateur> userByUserName = userService.findUserByUserName(usernameFromToken);
        Cart savedCart = null;
        Cart lCart = cartService.fetch_cart_by_id(userByUserName.get().getId());
        if(lCart!=null){
            lCart.updateArticleInformations(cart.getArticleInformations());
            savedCart = cartService.updateCart(lCart);
        }
        else{
            lCart = new Cart();
            lCart.updateArticleInformations(cart.getArticleInformations());
            savedCart = cartService.saveCart(lCart,userByUserName.get().getId());
        }
        return savedCart;
    }
}
