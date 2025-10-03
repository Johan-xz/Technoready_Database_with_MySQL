package com.consulting_scholar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.consulting_scholar")
public class ConsultingScholarApplication {

	public static void main(String[] args) {
        SpringApplication.run(ConsultingScholarApplication.class, args);
	}

}
