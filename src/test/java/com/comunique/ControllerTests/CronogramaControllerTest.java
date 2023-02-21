package com.comunique.ControllerTests;

import org.springframework.beans.factory.annotation.Autowired;

import com.comunique.controller.CronogramaController;
import com.comunique.service.UsuariosService;

public class CronogramaControllerTest {

    @Autowired
    CronogramaController cronogramaController;
    @Autowired
    UsuariosService usuariosService;

}
