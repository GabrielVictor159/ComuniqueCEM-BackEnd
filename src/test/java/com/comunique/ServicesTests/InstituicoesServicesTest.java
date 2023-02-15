package com.comunique.ServicesTests;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Instituicoes;
import com.comunique.service.InstituicoesService;

@SpringBootTest
public class InstituicoesServicesTest {
    @Autowired
    InstituicoesService instituicoesService;

    @Test
    public void testCadastro() {
        Assertions.assertEquals("Sucesso", cadastrarTest());
    }

    @Test
    public void loginUsuarioTet() {
        Assertions.assertEquals("Sucesso", loginUsuario());
    }

    @Test
    public void loginProfessorTet() {
        Assertions.assertEquals("Sucesso", loginProfessores());
    }

    public String cadastrarTest() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String loginUsuario() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Optional<Instituicoes> test = instituicoesService.LoginUsuario(instituicao.getNome(),
                    instituicao.getSenha());
            if (test.isEmpty()) {
                return "Não foi possivel fazer o login na instituição";
            }
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String loginProfessores() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Optional<Instituicoes> test = instituicoesService.LoginProfessores(instituicao.getNome(),
                    instituicao.getSenhaProfessores());
            if (test.isEmpty()) {
                return "Não foi possivel fazer o login na instituição";
            }
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
