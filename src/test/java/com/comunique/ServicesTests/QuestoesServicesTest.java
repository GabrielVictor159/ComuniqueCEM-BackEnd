package com.comunique.ServicesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Instituicoes;
import com.comunique.model.Questoes;
import com.comunique.service.InstituicoesService;
import com.comunique.service.QuestoesService;

@SpringBootTest
public class QuestoesServicesTest {
    @Autowired
    QuestoesService questoesService;
    @Autowired
    InstituicoesService instituicoesService;

    @Test
    public void cadastrarTest() {
        Assertions.assertEquals("Sucesso", cadastro());
    }

    @Test
    public void getTest() {
        Assertions.assertEquals("Sucesso", GetQuestao());
    }

    @Test
    public void getAllQuestoes() {
        Assertions.assertEquals("Sucesso", GetAllQuestao());
    }

    @Test
    public void Atualizar() {
        Assertions.assertEquals("Sucesso", Update());
    }

    public String cadastro() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Questoes a = ModelCadastrosTests.cadastrarQuestao(instituicao, questoesService);
            questoesService.Deletar(a.getIdQuestao());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String GetQuestao() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Questoes a = ModelCadastrosTests.cadastrarQuestao(instituicao, questoesService);
            questoesService.getQuestao(a.getIdQuestao());
            questoesService.Deletar(a.getIdQuestao());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String Update() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Questoes a = ModelCadastrosTests.cadastrarQuestao(instituicao, questoesService);
            a.setResposta1("asgfhfs");
            a.setResposta2("gdfdg");
            a.setResposta3("ytjgh");
            a.setTitulo("khjghf");
            a.setRespostaCorreta("reter");
            questoesService.Cadastrar(a);
            questoesService.Deletar(a.getIdQuestao());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String GetAllQuestao() {
        try {
            questoesService.getAllQuestoes();
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
