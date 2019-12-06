package it.usuratonkachi.populatorservicespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "it.usuratonkachi")
public class PopulatorServiceSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopulatorServiceSpringApplication.class, args);
	}

}
