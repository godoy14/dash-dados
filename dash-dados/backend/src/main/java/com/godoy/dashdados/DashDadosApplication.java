package com.godoy.dashdados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class DashDadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashDadosApplication.class, args);
	}

}
