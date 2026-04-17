package com.karapada.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class KarapadaSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(KarapadaSchoolApplication.class, args);
	}
	
}
