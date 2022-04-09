package com.github.iamhi.hiintegration.confusedseeker.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.github.iamhi.hiintegration.confusedseeker"})
public class ConfusedSeekerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfusedSeekerApplication.class, args);
	}

}
