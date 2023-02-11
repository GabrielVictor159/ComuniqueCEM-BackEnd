package com.comunique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class ComuniqueApplication  {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ComuniqueApplication.class, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    
}

