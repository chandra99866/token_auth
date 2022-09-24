package com.example.token.token;

import com.example.token.token.models.Clients;
import com.example.token.token.models.Role;
import com.example.token.token.service.ClientsImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class TokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}



	@Bean
	CommandLineRunner run(ClientsImpl clients){
		return args -> {
			clients.saveRole(new Role(null,"ROLE_USER"));
			clients.saveRole(new Role(null,"ROLE_MANAGER"));
			clients.saveRole(new Role(null,"ROLE_ADMIN"));
			clients.saveRole(new Role(null,"ROLE_CEO"));

			clients.saveClient(new Clients(null,"John T","john","1234",new ArrayList<>()));
			clients.saveClient(new Clients(null,"joushava v","joush","1234",new ArrayList<>()));
			clients.saveClient(new Clients(null,"will smith","will","1234",new ArrayList<>()));
			clients.saveClient(new Clients(null,"Arnalod d","arnold","1234",new ArrayList<>()));

			clients.addRoleToUser("john","ROLE_USER");
			clients.addRoleToUser("joush","ROLE_MANAGER");
			clients.addRoleToUser("will","ROLE_ADMIN");
			clients.addRoleToUser("arnold","ROLE_CEO");
			clients.addRoleToUser("arnold","ROLE_ADMIN");
			clients.addRoleToUser("arnold","ROLE_MANAGER");


		};
	}
}
