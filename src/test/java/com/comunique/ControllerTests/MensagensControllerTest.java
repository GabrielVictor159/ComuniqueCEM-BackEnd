package com.comunique.ControllerTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.comunique.controller.MensagensController;
import com.comunique.dto.MensagensDTO;
import com.comunique.functions.AleatoryString;
import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Chat;
import com.comunique.model.Instituicoes;
import com.comunique.model.Mensagens;
import com.comunique.model.Usuarios;
import com.comunique.service.ChatService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.MensagensService;
import com.comunique.service.UsuariosService;
import com.comunique.functions.Reflections;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment  = WebEnvironment.RANDOM_PORT)
public class MensagensControllerTest {
    @Autowired
    MensagensService  mensagensService;
    @Autowired
    ChatService chatService;
    @Autowired
    UsuariosService usuariosService;
    @Autowired
    InstituicoesService instituicoesService;
    @Autowired
    TestRestTemplate testRestTemplate;

    private Instituicoes instituicao;
    private Usuarios usuario1;
    private String senhaUsuario1 = AleatoryString.getAlphaNumericString(7);
    private Usuarios usuario2;
    private String senhaUsuario2 = AleatoryString.getAlphaNumericString(7);
    private Chat chat;
    private Mensagens mensagem1;

    @Before
    public void setUp(){
        this.instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
        this.usuario1 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, senhaUsuario1);
        this.usuario2 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, senhaUsuario2);
        this.chat = ModelCadastrosTests.CadastrarChat(usuario2, usuario1, chatService);
        this.mensagem1 = ModelCadastrosTests.CadastrarMensagem(chat, usuario1.getIdUsuario(), mensagensService);

    }

    @After
    public void setDown(){
        List<Chat> chatList = chatService.getChatByUser(usuario1);
        for(Chat c: chatList){
            mensagensService.DeleteForChat(c);
        }
        chatService.DeletarAllByUsuario(usuario1);
        chatService.DeletarAllByUsuario(usuario2);
        usuariosService.DeletarAllByInstituicao(instituicao);
        instituicoesService.Deletar(instituicao.getIdInstituicao());
    }

    @Test
    public void getMessageTest() throws NoSuchMethodException{
        String URI = Reflections.getURI(MensagensController.class, "getMessage");
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, mensagem1.getIdMensagens());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }

    @Test
    public void getAllMessageChatTest() throws NoSuchMethodException {
        String URI = Reflections.getURI(MensagensController.class, "getAllMessageChat");
        String novaSenha = AleatoryString.getAlphaNumericString(7);
        Usuarios novoUsuario = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, novaSenha);
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1,chat.getIdChat());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1+"5",chat.getIdChat());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, usuario1.getEmail(), senhaUsuario1,idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());
        ResponseEntity<Object> response4 = testRestTemplate.exchange(
                URI,
                HttpMethod.GET,
                null,
                Object.class, novoUsuario.getEmail(), novaSenha,chat.getIdChat());
        assertEquals(HttpStatus.UNAUTHORIZED, response4.getStatusCode());
    }

    @Test
    public void adicionarTest() throws NoSuchMethodException{
        String URI = Reflections.getURI(MensagensController.class, "adicionar");
        MensagensDTO dto = new MensagensDTO(usuario1.getIdUsuario(),"sadasdas",false, false, false);
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.POST,
                new HttpEntity<>(dto),
                Object.class, usuario1.getEmail(), senhaUsuario1,chat.getIdChat());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.POST,
                new HttpEntity<>(dto),
                Object.class, usuario1.getEmail(), senhaUsuario1+"5",chat.getIdChat());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.POST,
                new HttpEntity<>(dto),
                Object.class, usuario1.getEmail(), senhaUsuario1,idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());
    }

    @Test
    public void confirmarEntregaTest() throws NoSuchMethodException{
        String URI = Reflections.getURI(MensagensController.class, "confirmarEntrega");
        Mensagens mensagemTeste = ModelCadastrosTests.CadastrarMensagem(chat, usuario1.getIdUsuario(), mensagensService);
        Usuarios usuarioFalso = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "123");
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                null,
                Object.class, usuario2.getEmail(), senhaUsuario2,mensagemTeste.getIdMensagens());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                null,
                Object.class, usuario2.getEmail(), senhaUsuario2+"5",mensagemTeste.getIdMensagens());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                null,
                Object.class, usuarioFalso.getEmail(), "123",mensagemTeste.getIdMensagens());
        assertEquals(HttpStatus.UNAUTHORIZED, response3.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response4 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                null,
                Object.class, usuario2.getEmail(), senhaUsuario2,idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response4.getStatusCode());
    }
    @Test
    public void confirmarLidaTest() throws NoSuchMethodException{
        String URI = Reflections.getURI(MensagensController.class, "confirmarLida");
        Mensagens mensagemTeste = ModelCadastrosTests.CadastrarMensagem(chat, usuario1.getIdUsuario(), mensagensService);
        Usuarios usuarioFalso = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "123");
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                null,
                Object.class, usuario2.getEmail(), senhaUsuario2,mensagemTeste.getIdMensagens());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                null,
                Object.class, usuario2.getEmail(), senhaUsuario2+"5",mensagemTeste.getIdMensagens());
        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                null,
                Object.class, usuarioFalso.getEmail(), "123",mensagemTeste.getIdMensagens());
        assertEquals(HttpStatus.UNAUTHORIZED, response3.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response4 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                null,
                Object.class, usuario2.getEmail(), senhaUsuario2,idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response4.getStatusCode());
    }
    @Test
    public void deletarPedidoTestAndDeletarConfirmarTest() throws NoSuchMethodException{
        String URI = Reflections.getURI(MensagensController.class, "DeletarPedido");
        String URI2 = Reflections.getURI(MensagensController.class, "DeletarConfirmar");
        Mensagens mensagemTeste = ModelCadastrosTests.CadastrarMensagem(chat, usuario1.getIdUsuario(), mensagensService);
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.POST,
                new HttpEntity<>(mensagemTeste),
                Object.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI2,
                HttpMethod.DELETE,
                null,
                Object.class,mensagemTeste.getIdMensagens());
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        
    }
    @Test
    public void setLidaChat() throws NoSuchMethodException{
        String URI = Reflections.getURI(MensagensController.class, "confirmarLidaChat"); 
        Usuarios novoUsuario = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "123");
        Chat novoChat = ModelCadastrosTests.CadastrarChat(usuario1, novoUsuario, chatService);
        Mensagens message = ModelCadastrosTests.CadastrarMensagem(novoChat, novoUsuario.getIdUsuario(), mensagensService);
        ResponseEntity<Object> response = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                null,
                Object.class,novoUsuario.getEmail(), "123", novoChat.getIdChat());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Optional<Mensagens> messageAnalise = mensagensService.getMensagem(message.getIdMensagens());
        assertEquals(false,messageAnalise.get().getLida());
        ResponseEntity<Object> response2 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                null,
                Object.class,usuario1.getEmail(), senhaUsuario1, novoChat.getIdChat());
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        Optional<Mensagens> messageAnalise2 = mensagensService.getMensagem(message.getIdMensagens());
        assertEquals(true,messageAnalise2.get().getLida());
        ResponseEntity<Object> response3 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                null,
                Object.class,novoUsuario.getEmail(), "1235", novoChat.getIdChat());
        assertEquals(HttpStatus.UNAUTHORIZED, response3.getStatusCode());
        UUID idFalso = UUID.randomUUID();
        ResponseEntity<Object> response4 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                null,
                Object.class,novoUsuario.getEmail(), "123", idFalso);
        assertEquals(HttpStatus.NOT_FOUND, response4.getStatusCode());
        ResponseEntity<Object> response5 = testRestTemplate.exchange(
                URI,
                HttpMethod.PUT,
                null,
                Object.class,usuario2.getEmail(), senhaUsuario2, novoChat.getIdChat());
        assertEquals(HttpStatus.UNAUTHORIZED, response5.getStatusCode());

    }
}
