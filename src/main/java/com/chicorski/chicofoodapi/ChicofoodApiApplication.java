package com.chicorski.chicofoodapi;

import com.chicorski.chicofoodapi.core.io.Base64ProtocolResolver;
import com.chicorski.chicofoodapi.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class ChicofoodApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		var app = new SpringApplication(ChicofoodApiApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
//		SpringApplication.run(ChicofoodApiApplication.class, args);
	}

}
