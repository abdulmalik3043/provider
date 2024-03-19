package com.contract.maven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.contract")
@SpringBootApplication
public class ProviderCApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderCApplication.class, args);
    	System.out.println("hello");

	}

}
