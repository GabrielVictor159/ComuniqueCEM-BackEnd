package com.comunique.ServicesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Instituicoes;
import com.comunique.service.InstituicoesService;

@SpringBootTest
public class InstituicoesTest {
    @Autowired
    InstituicoesService instituicoesService;

    @Test
    public void testCadastro() {
        Assertions.assertEquals("Sucesso", cadastrarTest());
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
}
