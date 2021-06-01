package com.kosta.springbootproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"controller2", "com.kosta"})
@SpringBootApplication
public class SpringbootfirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootfirstApplication.class, args);
	}

}
