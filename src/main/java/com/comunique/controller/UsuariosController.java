package com.comunique.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comunique.model.Usuarios;
import com.comunique.service.UsuariosService;

@RestController
@RequestMapping(value = "/Usuarios", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin
public class UsuariosController {

    @Autowired
    UsuariosService usuariosService;

    @GetMapping("/{email}/{senha}")
    public ResponseEntity<Usuarios> Login(@PathVariable(value = "email") String email,
            @PathVariable(value = "senha") String senha) {
        Optional<Usuarios> usuarioLogin = usuariosService.Login(email, senha);
        if (usuarioLogin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<Usuarios>(usuarioLogin.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllUsersIntituto/{idInstituto}/{emailUsuario}/{senhaUsuario}")
    public ResponseEntity<List<Usuarios>> getAllUsuariosInstituto(@PathVariable(value = "idInstituto") UUID idInstituto,
            @PathVariable(value = "emailUsuario") String email, @PathVariable(value = "senhaUsuario") String senha) {
        Optional<Usuarios> usuarioLogin = usuariosService.Login(email, senha);
        if (usuarioLogin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        } else {
            if (usuarioLogin.get().getInstituicao().getIdInstituicao() != idInstituto) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<List<Usuarios>>(usuariosService.getAllUsuariosInstituicao(idInstituto),
                        HttpStatus.OK);
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> getUsuario(@PathVariable(value = "id") UUID id) {
        Optional<Usuarios> usuario = usuariosService.getUser(id);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<Usuarios>(usuario.get(), HttpStatus.OK);
        }
    }

}
