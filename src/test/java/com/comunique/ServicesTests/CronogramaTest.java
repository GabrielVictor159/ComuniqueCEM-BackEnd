package com.comunique.ServicesTests;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Cronograma;
import com.comunique.model.Instituicoes;
import com.comunique.model.Usuarios;
import com.comunique.service.CronogramaService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.UsuariosService;

@SpringBootTest
public class CronogramaTest {
    @Autowired
    CronogramaService cronogramaService;
    @Autowired
    UsuariosService usuariosService;
    @Autowired
    InstituicoesService instituicoesService;

    @Test
    public void testCadastro() {
        Assertions.assertEquals("Sucesso", Cadastro());
    }

    @Test
    public void testGetCronograma() {
        Assertions.assertEquals("Sucesso", GetCronograma());
    }

    @Test
    public void testGetUserCronograma() {
        Assertions.assertEquals("Sucesso", GetUserCronogramas());
    }

    public String Cadastro() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios user = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService);
            Cronograma cronograma = ModelCadastrosTests.CadastrarCronograma(user, cronogramaService);
            System.out.println(user);
            System.out.println(cronograma);
            cronogramaService.Deletar(cronograma.getIdCronograma());
            usuariosService.Deletar(user);
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public String GetCronograma() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios user = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService);
            Cronograma cronograma = ModelCadastrosTests.CadastrarCronograma(user, cronogramaService);
            Optional<Cronograma> test = cronogramaService.getCronograma(cronograma.getIdCronograma());
            System.out.println(test);
            cronogramaService.Deletar(cronograma.getIdCronograma());
            usuariosService.Deletar(user);
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String GetUserCronogramas() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios user = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService);
            Cronograma cronograma = ModelCadastrosTests.CadastrarCronograma(user, cronogramaService);
            List<Cronograma> list = cronogramaService.getCronogramaUser(user);
            System.out.println(list);
            cronogramaService.Deletar(cronograma.getIdCronograma());
            usuariosService.Deletar(user);
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
