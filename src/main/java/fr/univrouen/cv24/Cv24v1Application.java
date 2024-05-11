package fr.univrouen.cv24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "fr.univrouen.cv24v1")
@EnableJpaRepositories(basePackages = "fr.univrouen.cv24v1.repository")
@ComponentScan(basePackages = {"fr.univrouen.cv24v1.controllers","fr.univrouen.cv24v1.service"})
@EntityScan(basePackages = "fr.univrouen.cv24v1.model")
public class Cv24v1Application {

	public static void main(String[] args) {
		SpringApplication.run(Cv24v1Application.class, args);
	}

}
