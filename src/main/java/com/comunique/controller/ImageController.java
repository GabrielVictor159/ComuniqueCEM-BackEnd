package com.comunique.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.comunique.service.AdminsService;

@RestController
@RequestMapping(value = "/Images")
@CrossOrigin
public class ImageController {

    @Autowired
    AdminsService adminsService;

    @PostMapping("/admin/{adminNome}/{senhaAdmin}")
    public ResponseEntity<Object> uploadImage(@RequestParam("image") MultipartFile image,
            @PathVariable(value = "adminNome") String adminNome,
            @PathVariable(value = "senhaNome") String senhaAdmin) {
        if (adminsService.Login(adminNome, senhaAdmin).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                byte[] bytes = image.getBytes();
                Path path = Paths.get("src/main/resources/static/images/" + image.getOriginalFilename());
                Files.write(path, bytes);
                return ResponseEntity.status(HttpStatus.CREATED).body("Image uploaded successfully");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
            }
        }
    }

}
