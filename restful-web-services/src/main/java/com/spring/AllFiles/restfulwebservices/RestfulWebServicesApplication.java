package com.spring.AllFiles.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.spring.AllFiles.repository")
@SpringBootApplication
@EntityScan("com.spring.AllFiles.model")
@ComponentScan(basePackages= {"com.spring.AllFiles.exception","com.spring.AllFiles.swaggerConfig","com.spring.AllFiles.hello","com.spring.AllFiles.controller","com.spring.AllFiles.model","com.spring.AllFiles.service"})
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}

}
 