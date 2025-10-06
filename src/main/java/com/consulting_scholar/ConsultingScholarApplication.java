package com.consulting_scholar;
/**
 * This is where is imported all the Spring Boot frameworks for the use here.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.consulting_scholar")
@EnableJpaRepositories("com.consulting_scholar.repository")
@EntityScan("com.consulting_scholar.model")
public class ConsultingScholarApplication {
    /**
     * This is the main function builded by Spring boot initializr
     * marked as a @SpringBootApplication
     * and make connection with the Packages by the tag @ComponentScan
     *
     * @param args
     */

	public static void main(String[] args) {
        SpringApplication.run(ConsultingScholarApplication.class, args);
	}

}
