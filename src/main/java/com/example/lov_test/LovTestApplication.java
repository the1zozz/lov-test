package com.example.lov_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LovTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LovTestApplication.class, args);
	}

}
