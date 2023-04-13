package com.comunique.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comunique.model.Usuarios;
import com.comunique.service.UsuariosService;

@RestController
public class EmailVerificationController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        Optional<Usuarios> optionalUser = usuariosService.findByEmailVerificationToken(token);

        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>("Token inválido ou expirado", HttpStatus.BAD_REQUEST);
        }

        Usuarios user = optionalUser.get();

        if (user.isEmailVerified()) {
            return new ResponseEntity<>("O email já foi verificado", HttpStatus.BAD_REQUEST);
        }

        user.setEmailVerified(true);
        user.setEmailVerificationToken(null);

        usuariosService.Cadastrar(user);

        return new ResponseEntity<>("E-mail verificado com sucesso", HttpStatus.OK);
    }
}
