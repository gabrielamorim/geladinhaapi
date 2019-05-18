package com.gabrielamorim.zx.challenge.geladinhaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of this SpringBoot application. 
 * 
 * If you want to deploy this application in an app server
 * you must to extends @SpringBootServletInitializer
 * 
 * For details see SpringBoot specific {@link org.springframework.boot.SpringApplication} interface.
 * 
 * @author Gabriel Amorim
 */
@SpringBootApplication
public class GeladinhaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeladinhaApiApplication.class, args);
	}
}
