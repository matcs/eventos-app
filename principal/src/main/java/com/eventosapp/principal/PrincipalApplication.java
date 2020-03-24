package com.eventosapp.principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan (basePackages = {"com.eventosapp.controller"})
@EntityScan (basePackages = {"com.eventosapp.model"})
@EnableJpaRepositories("com.eventosapp.repository")
@SpringBootApplication
public class PrincipalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrincipalApplication.class, args);
	}

}
