package com.comunique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class ComuniqueApplication {

	public static void main(String[] args) {
		try {
		SpringApplication.run(ComuniqueApplication.class, args);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
