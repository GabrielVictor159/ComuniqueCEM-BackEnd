package com.comunique;

import com.comunique.dto.NoticiasDTO;
import com.comunique.model.Noticias;
import com.comunique.model.Usuarios;
import com.comunique.service.NoticiasService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NoticiasTest {
    @Autowired
    NoticiasService noticiasService;


    public Noticias cadastro(){
        NoticiasDTO a = new NoticiasDTO("sadas","dasasd","dsadas");
        Noticias b = new Noticias();
        BeanUtils.copyProperties(a,b);
      return noticiasService.Cadastrar(b);
    }

    @Test
    public void getNoticiaTest(){
        Assertions.assertEquals("Sucesso",getNoticia());
    }
    @Test
    public void atualizarTest(){
        Assertions.assertEquals("Sucesso",atualiza());
    }
    @Test
    public void getAllNoticiasTest(){
        Assertions.assertEquals("Sucesso",getAllNoticias());
    }
    public String atualiza(){
        try {
            Noticias noticia = cadastro();
            noticia.setImagem("asdas");
            noticia.setTexto("asdas");
            noticia.setTitulo("gerge");
            noticiasService.Cadastrar(noticia);
            noticiasService.Deletar(noticia);
            return "Sucesso";
        }
        catch (Exception e){
            return  e.getMessage();
        }
    }
    public String getAllNoticias(){
        try {
            noticiasService.getAllNoticias();
            return "Sucesso";
        }
        catch (Exception e){
            return  e.getMessage();
        }
    }
    public String getNoticia(){
        try {
            Noticias noticia = cadastro();
            noticiasService.getNoticia(noticia);
            noticiasService.Deletar(noticia);
            return "Sucesso";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }



}
