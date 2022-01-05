package com.next.demandanteGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemandanteGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemandanteGeneratorApplication.class, args);
	}

}
