package com.comunique.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comunique.service.UsuariosService;

@RestController
@RequestMapping(value = "/Usuarios", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin
public class UsuariosController {

    @Autowired
    UsuariosService usuariosService;

}
