package com.comunique.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comunique.model.AdminsMaster;
import com.comunique.service.AdminsMasterService;

@RestController
@RequestMapping(value = "/AdminsMaster", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin
public class AdminMasterController {
    @Autowired
    AdminsMasterService adminsMasterService;

    @GetMapping("/{nome}/{senha}")
    public ResponseEntity<Object> Login(@PathVariable String nome, @PathVariable String senha) {
        Optional<AdminsMaster> adminLogin = adminsMasterService.Login(nome, senha);
        if (adminLogin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(adminLogin.get(), HttpStatus.OK);
        }
    }
}
