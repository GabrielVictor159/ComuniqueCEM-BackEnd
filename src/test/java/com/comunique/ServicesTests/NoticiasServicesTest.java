package com.comunique.ServicesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Instituicoes;
import com.comunique.model.Noticias;
import com.comunique.service.InstituicoesService;
import com.comunique.service.NoticiasService;

@SpringBootTest
public class NoticiasServicesTest {
    @Autowired
    NoticiasService noticiasService;
    @Autowired
    InstituicoesService instituicoesService;

    @Test
    public void getNoticiaTest() {
        Assertions.assertEquals("Sucesso", getNoticia());
    }

    @Test
    public void atualizarTest() {
        Assertions.assertEquals("Sucesso", atualiza());
    }

    @Test
    public void getAllNoticiasTest() {
        Assertions.assertEquals("Sucesso", getAllNoticias());
    }

    public String atualiza() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Noticias noticia = ModelCadastrosTests.CadastrarNoticia(instituicao, noticiasService);
            noticia.setImagem("asdas");
            noticia.setTexto("asdas");
            noticia.setTitulo("gerge");
            noticiasService.Cadastrar(noticia);
            noticiasService.Deletar(noticia.getIdNoticia());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getAllNoticias() {
        try {
            noticiasService.getAllNoticias();
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getNoticia() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Noticias noticia = ModelCadastrosTests.CadastrarNoticia(instituicao, noticiasService);
            noticiasService.getNoticia(noticia.getIdNoticia());
            noticiasService.Deletar(noticia.getIdNoticia());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
