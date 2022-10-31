package com.springbootionic.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.springbootionic.cursomc.services.DBService;
import com.springbootionic.cursomc.services.EmailService;
import com.springbootionic.cursomc.services.MockEmailService;
import com.springbootionic.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {

		if (!strategy.equals("create")) {
			return false;
		} else {
			dbService.instantiateTestDatabase();
			return true;
		}

	}

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	
	@Bean
	public MockEmailService mockEmailService() {
		return new MockEmailService();
	}

}