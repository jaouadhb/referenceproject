package fr.reference.it.referenceproject.aspect;

import fr.reference.it.referenceproject.domaine.dto.Cart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Aspect
@Component
public class CartAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartAspect.class);

    @Around("execution(* fr.reference.it.referenceproject.controller.CartController(..)))")
    public Object cardInformation(ProceedingJoinPoint pProceedingJoinPoint) throws Throwable {
        Cart cart = (Cart) pProceedingJoinPoint.proceed();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(Objects.nonNull(authentication)){
            User principal = (User) authentication.getPrincipal();
            String format = String.format("Articles %s reserved by %s ", cart.getArticleInformations().stream().map(articleInformation -> articleInformation.getArticle().getName()).collect(Collectors.joining(",")), principal.getUsername());
            LOGGER.info(format);
        }
        return cart;
    }

}
