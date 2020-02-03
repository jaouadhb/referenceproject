package fr.reference.it.referenceproject.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ArticleAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleAspect.class);

    @Around("execution(* fr.reference.it.referenceproject.controller.ArticleController..*(..)))")
    public Object allMethodes(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        if(auth != null)
        {
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if("fetch_article_byID".equals(methodName)) {
                LOGGER.info("The User "+principal.getUsername()+" Select the product with id : "+proceedingJoinPoint.getArgs()[0]);
            }
        }
        stopWatch.stop();
        LOGGER.info("Execution time of " + className + "." + methodName + " "
                + ":: " + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }

}
