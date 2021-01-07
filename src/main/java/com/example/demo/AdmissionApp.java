package com.example.demo;

import com.example.demo.model.Account;
import com.example.demo.model.PublicEnumType;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdmissionApp {
	@Autowired
	private UserRepository up;
	public static void main(String[] args) {
		SpringApplication.run(AdmissionApp.class, args);
	}

	@Bean
	InitializingBean initDatabase() {
		return () -> {
			up.save(new Account(1, PublicEnumType.PermissionType.ADMIN));
		};
	}
}
