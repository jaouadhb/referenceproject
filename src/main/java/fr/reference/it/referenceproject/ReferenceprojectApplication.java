package fr.reference.it.referenceproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@ComponentScan({"fr.reference.it.referenceproject.security","fr.reference.it.referenceproject.dataacces"})
@PropertySource("classpath:application.properties")
public class ReferenceprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReferenceprojectApplication.class, args);
    }

}
