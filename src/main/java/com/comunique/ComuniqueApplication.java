package com.comunique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ComuniqueApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ComuniqueApplication.class, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
