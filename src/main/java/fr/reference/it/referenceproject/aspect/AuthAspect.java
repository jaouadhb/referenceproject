package fr.reference.it.referenceproject.aspect;

import fr.reference.it.referenceproject.security.jwt.model.JwtTokenResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuthAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthAspect.class);

    @Around("execution(* fr.reference.it.referenceproject.controller.AuthenticationController.createAuthenticationToken(..)))")
    public Object whenLogin(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ResponseEntity result = (ResponseEntity) proceedingJoinPoint.proceed();
        if(result.getStatusCode().is2xxSuccessful()){
            LocalDateTime localDateTime = LocalDateTime.now();
            JwtTokenResponse jwtTokenResponse = (JwtTokenResponse) result.getBody();
            LOGGER.info("The User "+jwtTokenResponse.getUsername()+" Is connected at "+localDateTime);
        }
        return result;
    }
}
