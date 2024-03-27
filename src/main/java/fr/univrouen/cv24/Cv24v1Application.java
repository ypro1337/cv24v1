package fr.univrouen.cv24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"fr.univrouen.cv24v1.controllers"})
public class Cv24v1Application {

	public static void main(String[] args) {
		SpringApplication.run(Cv24v1Application.class, args);
	}

}
