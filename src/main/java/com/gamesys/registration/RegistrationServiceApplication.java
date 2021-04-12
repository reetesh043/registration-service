package com.gamesys.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main class of registration api.
 */

@EnableCaching
@EnableScheduling
@EnableAsync
@SpringBootApplication
public class RegistrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationServiceApplication.class, args);
	}

}
