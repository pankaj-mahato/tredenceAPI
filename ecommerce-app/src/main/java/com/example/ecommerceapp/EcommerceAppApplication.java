package com.example.ecommerceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = "com.example.ecommerceapp.model")
@SpringBootApplication
public class EcommerceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceAppApplication.class, args);
	}

}
