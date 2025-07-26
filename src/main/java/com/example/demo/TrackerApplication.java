/*************************************************************
 * @Author: Salma Boukdad
 * @Filename: TrackerApplication.java
 * @Date: 05/13/25
 * @Description: This application uses Spring Boot, Maven, DB's,
 * 				 Thymeleaf, and Bootstrap for CSS to create a simple and professional
 * 				 fitness tracker - users can enter a Record entry and be notified of their
 * 				 newly added or most recently deleted record along with updated total mileage
 */

package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TrackerApplication class
 */
@SpringBootApplication
public class TrackerApplication {

	/**
	 * Initialize Spring Application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(TrackerApplication.class, args);
	}
}