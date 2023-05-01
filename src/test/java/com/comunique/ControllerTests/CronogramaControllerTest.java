package com.comunique.ControllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
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

import com.comunique.controller.CronogramaController;
import com.comunique.dto.CronogramaDTO;
import com.comunique.functions.AleatoryString;
import com.comunique.functions.ModelCadastrosTests;
import com.comunique.functions.Reflections;
import com.comunique.model.Cronograma;
import com.comunique.model.Instituicoes;
import com.comunique.model.Usuarios;
import com.comunique.service.CronogramaService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.UsuariosService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CronogramaControllerTest {

    @Autowired
    CronogramaService cronogramaService;
    @Autowired
    UsuariosService usuariosService;
    @Autowired
    InstituicoesService instituicoesService;

    @Autowired
    TestRestTemplate testRestTemplate;

    private Instituicoes instituicao;
    private Usuarios usuario;
    private Cronograma cronograma;
    private String senhaUsuario = AleatoryString.getAlphaNumericString(7);

    @Before
    public void setUp() throws ParseException {
        this.instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
        this.usuario = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, senhaUsuario);
        this.cronograma = ModelCadastrosTests.CadastrarCronograma(usuario, cronogramaService);
    }

    @After
    public void setDown() {
        List<Usuarios> listUser = usuariosService.getAllUsuariosInstituicao(instituicao);
        for (Usuarios use : listUser) {
            cronogramaService.DeletarAllByUsuario(use);
        }
        usuariosService.DeletarAllByInstituicao(instituicao);
        instituicoesService.Deletar(instituicao.getIdInstituicao());
    }

    @Test
    public void getCronogramaTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(CronogramaController.class, "findCronograma");
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuario.getEmail(), senhaUsuario, cronograma.getIdCronograma());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuario.getEmail(), senhaUsuario + "5", cronograma.getIdCronograma());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuario.getEmail(), senhaUsuario, idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());

    }

    @Test
    public void getCronogramaByUserTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(CronogramaController.class, "getAll");
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuario.getEmail(), senhaUsuario);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuario.getEmail(), senhaUsuario + "5");
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
    }

    @Test
    public void cadastrarCronogramaTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(CronogramaController.class, "cadastrarCronograma");
        Date data = new Date();
        CronogramaDTO dto = new CronogramaDTO(data, "blue", 15, AleatoryString.getAlphaNumericString(7));
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.POST,
                new HttpEntity<>(dto),
                Object.class, usuario.getEmail(), senhaUsuario);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.POST,
                new HttpEntity<>(dto),
                Object.class, usuario.getEmail(), senhaUsuario + "5");
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
    }

    @Test
    public void alterarCronogramaTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(CronogramaController.class, "AlterarCronograma");
        Date data = new Date();
        CronogramaDTO dto = new CronogramaDTO(data, "blue", 15, AleatoryString.getAlphaNumericString(7));
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                new HttpEntity<>(dto),
                Object.class, usuario.getEmail(), senhaUsuario + "5", cronograma.getIdCronograma());
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                new HttpEntity<>(dto),
                Object.class, usuario.getEmail(), senhaUsuario, idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                new HttpEntity<>(dto),
                Object.class, usuario.getEmail(), senhaUsuario, cronograma.getIdCronograma());
        assertEquals(HttpStatus.OK, response3.getStatusCode());
        System.out.println(response.getBody());
    }

    @Test
    public void deletarCronogramaTest() throws NoSuchMethodException, ParseException {
        String URI = Reflections.getURI(CronogramaController.class, "DeletarCronograma");
        Cronograma novoCronograma = ModelCadastrosTests.CadastrarCronograma(usuario, cronogramaService);
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class, usuario.getEmail(), senhaUsuario + "5", novoCronograma.getIdCronograma());
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class, usuario.getEmail(), senhaUsuario, idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class, usuario.getEmail(), senhaUsuario, novoCronograma.getIdCronograma());
        assertEquals(HttpStatus.OK, response3.getStatusCode());
    }

    @Test
    public void deletarAllCronogramaTest() throws NoSuchMethodException, ParseException {
        String URI = Reflections.getURI(CronogramaController.class, "DeletarAllCronograma");
        String senha = AleatoryString.getAlphaNumericString(7);
        Usuarios user = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, senha);
        Cronograma novoCronograma = ModelCadastrosTests.CadastrarCronograma(user, cronogramaService);
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class, user.getEmail(), senha + "5");
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class, user.getEmail(), senha);
        assertEquals(HttpStatus.OK, response2.getStatusCode());

    }
}
