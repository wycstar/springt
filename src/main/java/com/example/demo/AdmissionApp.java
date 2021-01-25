package com.example.demo;

import com.example.demo.model.Login;
import com.example.demo.model.Account;
import com.example.demo.model.PublicEnumType;
import com.example.demo.model.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdmissionApp {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private LoginRepository loginRepo;
	public static void main(String[] args) {
		SpringApplication.run(AdmissionApp.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			Account account = new Account(PublicEnumType.PermissionType.ADMIN);
			User user = new User("admin", "1@q.com", "186000", account);
			Login login = new Login("admin", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220", user);
			accountRepo.save(account);
			userRepo.save(user);
			loginRepo.save(login);
		};
	}
}
