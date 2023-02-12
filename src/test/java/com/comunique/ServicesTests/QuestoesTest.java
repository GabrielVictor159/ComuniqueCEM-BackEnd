package com.comunique.ServicesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunique.dto.QuestoesDTO;
import com.comunique.model.Questoes;
import com.comunique.service.QuestoesService;

@SpringBootTest
public class QuestoesTest {
    @Autowired
    QuestoesService questoesService;

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
            Questoes a = cadastrar("asda", "asdas", "htrgfh", "4rretge", "rthrt", "dasda");
            questoesService.Deletar(a.getIdQuestao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String GetQuestao() {
        try {
            Questoes a = cadastrar("asda", "asdas", "htrgfh", "4rretge", "rthrt", "dasda");
            questoesService.getQuestao(a.getIdQuestao());
            questoesService.Deletar(a.getIdQuestao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String Update() {
        try {
            Questoes a = cadastrar("asda", "asd", "htrgfh", "4rretge", "rthrt", "dasda");
            a.setResposta1("asgfhfs");
            a.setResposta2("gdfdg");
            a.setResposta3("ytjgh");
            a.setTitulo("khjghf");
            a.setRespostaCorreta("reter");
            questoesService.Cadastrar(a);
            questoesService.Deletar(a.getIdQuestao());
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

    public Questoes cadastrar(String titulo, String resposta1, String resposta2, String resposta3, String resposta4,
            String RespostaCorreta) {
        QuestoesDTO questao = new QuestoesDTO(titulo, resposta1, resposta2, resposta3, resposta4, RespostaCorreta);
        Questoes a = new Questoes();
        BeanUtils.copyProperties(questao, a);
        return questoesService.Cadastrar(a);
    }
}
