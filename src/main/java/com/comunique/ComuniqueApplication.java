package com.comunique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Instituicoes;
import com.comunique.model.Usuarios;
import com.comunique.service.InstituicoesService;
import com.comunique.service.UsuariosService;

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
