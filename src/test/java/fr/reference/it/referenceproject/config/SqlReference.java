package fr.reference.it.referenceproject.config;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Sql(scripts = "classpath:scripts/preData.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public @interface SqlReference {

}
