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

import com.comunique.controller.AdminController;
import com.comunique.dto.AdminsDTO;
import com.comunique.functions.ModelCadastrosTests;
import com.comunique.functions.Reflections;
import com.comunique.model.Admins;
import com.comunique.model.Instituicoes;
import com.comunique.service.AdminsService;
import com.comunique.service.InstituicoesService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AdminControllerTest {
    @Autowired
    InstituicoesService instituicoesService;
    @Autowired
    AdminsService adminsService;
    @Autowired
    TestRestTemplate testRestTemplate;

    private Admins admin;
    private String senhaAdmin;
    private Instituicoes instituicao;

    @Before
    public void setUp() {
        this.instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
        this.senhaAdmin = "159487";
        this.admin = ModelCadastrosTests.CadastrarAdmin(instituicao, adminsService, senhaAdmin);
    }

    @After
    public void setDown() {
        adminsService.DeletarAllByInstituicao(instituicao);
        instituicoesService.Deletar(instituicao.getIdInstituicao());
    }

    @Test
    public void loginTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(AdminController.class, "getAdmin");
        ResponseEntity<Object> response = testRestTemplate.getForEntity(URI,
                Object.class,
                admin.getNome(),
                senhaAdmin);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity<Object> response2 = testRestTemplate.getForEntity(URI,
                Object.class,
                admin.getNome(),
                senhaAdmin + "8");
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());

    }

    @Test
    public void getAllUser() throws NoSuchMethodException {
        String URI = Reflections.getURI(AdminController.class, "getAll");
        ResponseEntity<Object> response = testRestTemplate.getForEntity(URI,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity<Object> response2 = testRestTemplate.getForEntity(URI,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha + "5");
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
    }

    @Test
    public void getOneUser() throws NoSuchMethodException {
        String URI = Reflections.getURI(AdminController.class, "getOneAdmin");
        ResponseEntity<Object> response = testRestTemplate.getForEntity(
                URI,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha,
                admin.getIdAdmin());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity<Object> response2 = testRestTemplate.getForEntity(
                URI,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha + "595",
                admin.getIdAdmin());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response3 = testRestTemplate.getForEntity(
                URI,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha,
                idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());
    }

    @Test
    public void getAllByInstituto() throws NoSuchMethodException {
        String URI = Reflections.getURI(AdminController.class, "getAllByInstituto");
        ResponseEntity<Object> response = testRestTemplate.getForEntity(
                URI,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha,
                instituicao.getIdInstituicao());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response2 = testRestTemplate.getForEntity(
                URI,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha,
                idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
        ResponseEntity<Object> response3 = testRestTemplate.getForEntity(
                URI,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha + "sdas",
                instituicao.getIdInstituicao());
        assertEquals(HttpStatus.UNAUTHORIZED, response3.getStatusCode());
    }

    @Test
    public void cadastrarAdmin() throws NoSuchMethodException {
        String URI = Reflections.getURI(AdminController.class, "adicionar");
        AdminsDTO admin = new AdminsDTO("easesa", "dasdas");
        ResponseEntity<Object> response = testRestTemplate.postForEntity(
                URI,
                admin,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha,
                instituicao.getIdInstituicao());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response2 = testRestTemplate.postForEntity(
                URI,
                admin,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha,
                idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
        ResponseEntity<Object> response3 = testRestTemplate.postForEntity(
                URI,
                admin,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha + "5",
                instituicao.getIdInstituicao());
        assertEquals(HttpStatus.UNAUTHORIZED, response3.getStatusCode());
    }

    @Test
    public void alterarTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(AdminController.class, "alterar");
        AdminsDTO admin2 = new AdminsDTO("ewa", "dsc");
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                new HttpEntity<>(admin2),
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha,
                admin.getIdAdmin());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                new HttpEntity<>(admin2),
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha + "a",
                admin.getIdAdmin());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                new HttpEntity<>(admin2),
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha,
                idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());

    }

    @Test
    public void DeletarTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(AdminController.class, "deletar");
        String senha = "dasdas";
        Admins admin2 = ModelCadastrosTests.CadastrarAdmin(instituicao, adminsService, senha);
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha,
                admin2.getIdAdmin());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Admins admin3 = ModelCadastrosTests.CadastrarAdmin(instituicao, adminsService, senha);
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha + "a",
                admin3.getIdAdmin());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        Admins admin4 = ModelCadastrosTests.CadastrarAdmin(instituicao, adminsService, senha);
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class,
                AdminMasterExample.nome,
                AdminMasterExample.senha,
                idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());

    }

}
