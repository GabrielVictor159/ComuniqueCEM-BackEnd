package com.comunique.ControllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.comunique.controller.ChatController;
import com.comunique.functions.AleatoryString;
import com.comunique.functions.ModelCadastrosTests;
import com.comunique.functions.Reflections;
import com.comunique.model.Chat;
import com.comunique.model.Instituicoes;
import com.comunique.model.Usuarios;
import com.comunique.service.ChatService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.MensagensService;
import com.comunique.service.UsuariosService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ChatControllerTest {

    @Autowired
    UsuariosService usuariosService;
    @Autowired
    InstituicoesService instituicoesService;
    @Autowired
    ChatService chatService;
    @Autowired
    MensagensService mensagensService;
    @Autowired
    TestRestTemplate testRestTemplate;
    private Instituicoes instituicao;
    private Usuarios usuario1;
    private String senhaUsuario1 = AleatoryString.getAlphaNumericString(7);
    private Usuarios usuario2;
    private String senhaUsuario2 = AleatoryString.getAlphaNumericString(7);
    private Chat chat;

    @Before
    public void setUp() {
        this.instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
        this.usuario1 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, senhaUsuario1);
        this.usuario2 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, senhaUsuario2);
        this.chat = ModelCadastrosTests.CadastrarChat(usuario1, usuario2, chatService);
        ModelCadastrosTests.CadastrarMensagem(chat, usuario1.getIdUsuario(), mensagensService);
    }

    @After
    public void setDown() {
        List<Usuarios> usuarios = usuariosService.getAllUsuariosInstituicao(instituicao);
        for (Usuarios user : usuarios) {
            List<Chat> listChat = chatService.getChatByUser(user);
            for (Chat a : listChat) {
                mensagensService.DeleteForChat(a);
            }
        }
        for (Usuarios user : usuarios) {
            chatService.DeletarAllByUsuario(user);
        }
        usuariosService.DeletarAllByInstituicao(instituicao);
        instituicoesService.Deletar(instituicao.getIdInstituicao());

    }

    @Test
    public void getOneTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(ChatController.class, "getOne");
        String senhaTeste = AleatoryString.getAlphaNumericString(7);
        Usuarios usuarioTeste = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, senhaTeste);
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1, chat.getIdChat());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1 + "5", chat.getIdChat());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuarioTeste.getEmail(), senhaTeste, chat.getIdChat());
        assertEquals(HttpStatus.UNAUTHORIZED, response3.getStatusCode());
        UUID idFalse = UUID.randomUUID();
        ResponseEntity<Object> response4 = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1, idFalse);
        assertEquals(HttpStatus.NOT_FOUND, response4.getStatusCode());

    }

    @Test
    public void getAllTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(ChatController.class, "getAll");
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1 + "5");
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
    }

    @Test
    public void cadastrarTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(ChatController.class, "Cadastrar");
        String senhaTeste = AleatoryString.getAlphaNumericString(7);
        Usuarios usuarioTeste = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, senhaTeste);
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.POST,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1, usuarioTeste.getIdUsuario());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.POST,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1 + "5", usuarioTeste.getIdUsuario());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.POST,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1, idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());
        ResponseEntity<Object> response4 = testRestTemplate.exchange(
                URI,
                HttpMethod.POST,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1, usuarioTeste.getIdUsuario());
        assertEquals(HttpStatus.CONFLICT, response4.getStatusCode());
    }

    @Test
    public void deletarTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(ChatController.class, "Cadastrar");
        String senhaTeste = AleatoryString.getAlphaNumericString(7);
        Usuarios usuarioTeste = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, senhaTeste);
        Chat novoChat = ModelCadastrosTests.CadastrarChat(usuario1, usuarioTeste, chatService);
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1 + "5", novoChat.getIdChat());
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1, idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class, usuario2.getEmail(), senhaUsuario2, novoChat.getIdChat());
        assertEquals(HttpStatus.UNAUTHORIZED, response3.getStatusCode());
        ResponseEntity<Object> response4 = testRestTemplate.exchange(
                URI,
                HttpMethod.DELETE,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1, novoChat.getIdChat());
        assertEquals(HttpStatus.OK, response4.getStatusCode());
    }

}
