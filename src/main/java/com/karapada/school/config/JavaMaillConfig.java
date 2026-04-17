package com.karapada.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class JavaMaillConfig {
	
	@Bean
	public JavaMailSender javaMailSender()
	{
		return new JavaMailSenderImpl();
	}

}
