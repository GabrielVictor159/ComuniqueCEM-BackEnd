package com.comunique.ControllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.comunique.controller.NoticiasController;
import com.comunique.dto.NoticiasDTO;
import com.comunique.functions.ModelCadastrosTests;
import com.comunique.functions.Reflections;
import com.comunique.model.Admins;
import com.comunique.model.Instituicoes;
import com.comunique.model.Noticias;
import com.comunique.service.AdminsService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.NoticiasService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NoticiasControllerTest {

    @Autowired
    InstituicoesService instituicoesService;

    @Autowired
    NoticiasService noticiasService;
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    AdminsService adminsService;

    private Instituicoes instituicao;
    private Noticias noticia;
    private String senhaAdmin = "5616";
    private Admins admin;

    @Before
    public void setUp() {
        this.instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
        this.noticia = ModelCadastrosTests.CadastrarNoticia(instituicao, noticiasService);
        this.admin = ModelCadastrosTests.CadastrarAdmin(instituicao, adminsService, senhaAdmin);
    }

    @After
    public void setDown() {
        noticiasService.DeletarAllByInstituicao(instituicao);
        adminsService.DeletarAllByInstituicao(instituicao);
        instituicoesService.Deletar(instituicao.getIdInstituicao());
    }

    @Test
    public void getAllNoticiasPageTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(NoticiasController.class, "getAllNoticiasPage");
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class,
                instituicao.getIdInstituicao(),
                0, 10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class,
                idFalso,
                0, 10);
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());

    }

    @Test
    public void getNoticiaTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(NoticiasController.class, "getNoticia");
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class,
                noticia.getIdNoticia());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class,
                idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());

    }

    @Test
    public void cadastrarNoticiaTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(NoticiasController.class, "cadastrarNoticia");
        NoticiasDTO dto = new NoticiasDTO("dasdas", "adasasd", "dasdas");
        ResponseEntity<Object> response = testRestTemplate.postForEntity(
                URI,
                dto,
                Object.class,
                admin.getNome(),
                senhaAdmin);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity<Object> response2 = testRestTemplate.postForEntity(
                URI,
                dto,
                Object.class,
                admin.getNome(),
                senhaAdmin + "5");
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());

    }

    @Test
    public void updateNoticiaTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(NoticiasController.class, "updateNoticia");
        NoticiasDTO dto = new NoticiasDTO("dasdas", "adasasd", "dasdas");
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                new HttpEntity<>(dto),
                Object.class,
                admin.getNome(),
                senhaAdmin,
                noticia.getIdNoticia());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                new HttpEntity<>(dto),
                Object.class,
                admin.getNome(),
                senhaAdmin + "a",
                noticia.getIdNoticia());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                new HttpEntity<>(dto),
                Object.class,
                admin.getNome(),
                senhaAdmin,
                idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());
    }

    @Test
    public void deletarNoticia() throws NoSuchMethodException {
        String URI = Reflections.getURI(NoticiasController.class, "deleteNoticia");
        Noticias noticia2 = ModelCadastrosTests.CadastrarNoticia(instituicao, noticiasService);
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class,
                admin.getNome(),
                senhaAdmin,
                idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class,
                admin.getNome(),
                senhaAdmin + "5",
                noticia2.getIdNoticia());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class,
                admin.getNome(),
                senhaAdmin,
                noticia2.getIdNoticia());
        assertEquals(HttpStatus.OK, response3.getStatusCode());

    }
}
