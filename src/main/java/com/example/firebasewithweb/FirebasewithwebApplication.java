package com.example.firebasewithweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FirebasewithwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirebasewithwebApplication.class, args);
	}

}
