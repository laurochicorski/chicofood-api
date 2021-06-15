package com.chicorski.chicofoodapi;

import com.chicorski.chicofoodapi.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class ChicofoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChicofoodApiApplication.class, args);
	}

}
