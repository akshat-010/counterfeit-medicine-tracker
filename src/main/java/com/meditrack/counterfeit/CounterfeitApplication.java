package com.meditrack.counterfeit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

// when Spring Data JPA included in spring initializr setup
// it automatically looks for a database connection
// Temporarily Disable Database Auto-Configuration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CounterfeitApplication {

	public static void main(String[] args) {
		SpringApplication.run(CounterfeitApplication.class, args);

		System.out.println("Hello World");
	}

}
