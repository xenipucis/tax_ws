package com.my_impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.my_impl")
public class MainApplication {

	public static void main(final String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
