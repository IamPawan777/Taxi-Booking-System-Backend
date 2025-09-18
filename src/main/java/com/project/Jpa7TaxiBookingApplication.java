package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Jpa7TaxiBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(Jpa7TaxiBookingApplication.class, args);
	}

}
