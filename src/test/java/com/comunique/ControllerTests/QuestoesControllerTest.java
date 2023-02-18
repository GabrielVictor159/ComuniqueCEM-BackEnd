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

import com.comunique.controller.QuestoesController;
import com.comunique.dto.QuestoesDTO;
import com.comunique.functions.ModelCadastrosTests;
import com.comunique.functions.Reflections;
import com.comunique.model.Admins;
import com.comunique.model.Instituicoes;
import com.comunique.model.Questoes;
import com.comunique.service.AdminsService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.QuestoesService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class QuestoesControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    InstituicoesService instituicoesService;
    @Autowired
    AdminsService adminsService;
    @Autowired
    QuestoesService questoesService;
    private Instituicoes instituicao;
    private Admins admin;
    private String senhaAdmin = "1561";
    private Questoes questao;

    @Before
    public void setUp() {
        this.instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
        this.admin = ModelCadastrosTests.CadastrarAdmin(instituicao, adminsService, senhaAdmin);
        this.questao = ModelCadastrosTests.cadastrarQuestao(instituicao, questoesService);
    }

    @After
    public void setDown() {
        questoesService.DeletarAllByInstituicao(instituicao);
        adminsService.DeletarAllByInstituicao(instituicao);
        instituicoesService.Deletar(instituicao.getIdInstituicao());
    }

    @Test
    public void getOne() throws NoSuchMethodException {
        String URI = Reflections.getURI(QuestoesController.class, "findQuestionIp");
        ResponseEntity<Object> response = testRestTemplate.getForEntity(URI,
                Object.class,
                questao.getIdQuestao());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response2 = testRestTemplate.getForEntity(URI,
                Object.class,
                idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());

    }

    @Test
    public void getLimitRange() throws NoSuchMethodException {
        String URI = Reflections.getURI(QuestoesController.class, "findQuestionLimit");
        ResponseEntity<Object> response = testRestTemplate.getForEntity(URI,
                Object.class, instituicao.getIdInstituicao(), 10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response2 = testRestTemplate.getForEntity(URI,
                Object.class, idFalso, 10);
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }

    @Test
    public void findAll() throws NoSuchMethodException {
        String URI = Reflections.getURI(QuestoesController.class, "findAll");
        ResponseEntity<Object> response = testRestTemplate.getForEntity(URI,
                Object.class, instituicao.getIdInstituicao());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response2 = testRestTemplate.getForEntity(URI,
                Object.class, idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }

    @Test
    public void CadastrarTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(QuestoesController.class, "cadastrarQuestion");
        QuestoesDTO questao = new QuestoesDTO("asdas", "fatyghg", "fasfas", "fas", "gerge", "gerge");
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.POST,
                new HttpEntity<>(questao),
                Object.class,
                admin.getNome(),
                senhaAdmin);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.POST,
                new HttpEntity<>(questao),
                Object.class,
                admin.getNome(),
                senhaAdmin + "a");
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
    }

    @Test
    public void AlterarQuestap() throws NoSuchMethodException {
        String URI = Reflections.getURI(QuestoesController.class, "updateQuestao");
        QuestoesDTO questao2 = new QuestoesDTO("asdas", "fatyghg", "fasfas", "fas", "gerge", "gerge");
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                new HttpEntity<>(questao2),
                Object.class,
                admin.getNome(),
                senhaAdmin, questao.getIdQuestao());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                new HttpEntity<>(questao2),
                Object.class,
                admin.getNome(),
                senhaAdmin + "5", questao.getIdQuestao());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                new HttpEntity<>(questao2),
                Object.class,
                admin.getNome(),
                senhaAdmin, idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());
    }

    @Test
    public void ExcluirQuestao() throws NoSuchMethodException {
        String URI = Reflections.getURI(QuestoesController.class, "deleteQuestion");
        Questoes questao2 = ModelCadastrosTests.cadastrarQuestao(instituicao, questoesService);
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class,
                admin.getNome(),
                senhaAdmin, questao2.getIdQuestao());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class,
                admin.getNome(),
                senhaAdmin + "5", questao2.getIdQuestao());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class,
                admin.getNome(),
                senhaAdmin, idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());

    }
}
