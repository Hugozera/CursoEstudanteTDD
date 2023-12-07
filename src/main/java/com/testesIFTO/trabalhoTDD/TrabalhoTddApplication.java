package com.testesIFTO.trabalhoTDD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.testesIFTO.trabalhoTDD")
public class TrabalhoTddApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoTddApplication.class, args);
	}

}
