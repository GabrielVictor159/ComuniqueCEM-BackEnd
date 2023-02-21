package com.comunique.ServicesTests;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Chat;
import com.comunique.model.Instituicoes;
import com.comunique.model.Mensagens;
import com.comunique.model.Usuarios;
import com.comunique.service.ChatService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.MensagensService;
import com.comunique.service.UsuariosService;

@SpringBootTest
public class MensagensServicesTest {

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    ChatService chatService;

    @Autowired
    MensagensService mensagensService;

    @Autowired
    InstituicoesService instituicoesService;

    @Test
    public void cadastrarMensagemTest() {
        Assertions.assertEquals("Sucesso", CadastrarMensagemTest());
    }

    @Test
    public void getAllMensagensForChatTest() {
        Assertions.assertEquals("Sucesso", GetAllMensagensForChatTest());
    }

    @Test
    public void deleteInMensagensTest() {
        Assertions.assertEquals("Sucesso", DeleteInMensagensTest());
    }

    public String DeleteInMensagensTest() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios user1 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            Usuarios user2 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            Chat chat = ModelCadastrosTests.CadastrarChat(user1, user2, chatService);
            Mensagens mensagem1 = ModelCadastrosTests.CadastrarMensagem(chat, user1.getIdUsuario(), mensagensService);
            Mensagens mensagem2 = ModelCadastrosTests.CadastrarMensagem(chat, user2.getIdUsuario(), mensagensService);
            List<UUID> idMensagens = new ArrayList<>();
            idMensagens.add(mensagem1.getIdMensagens());
            idMensagens.add(mensagem2.getIdMensagens());
            System.out.println(idMensagens);
            mensagensService.DeleteIn(idMensagens);
            chatService.Deletar(chat);
            usuariosService.Deletar(user1.getIdUsuario());
            usuariosService.Deletar(user2.getIdUsuario());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String GetAllMensagensForChatTest() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios user1 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            Usuarios user2 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            Chat chat = ModelCadastrosTests.CadastrarChat(user1, user2, chatService);
            Mensagens mensagem1 = ModelCadastrosTests.CadastrarMensagem(chat, user1.getIdUsuario(), mensagensService);
            Mensagens mensagem2 = ModelCadastrosTests.CadastrarMensagem(chat, user2.getIdUsuario(), mensagensService);
            List<Mensagens> test = mensagensService.getAllMensagensForChat(chat);
            System.out.println(test);
            mensagensService.DeleteForChat(chat);
            chatService.Deletar(chat);
            usuariosService.Deletar(user1.getIdUsuario());
            usuariosService.Deletar(user2.getIdUsuario());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String CadastrarMensagemTest() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios user1 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            Usuarios user2 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            Chat chat = ModelCadastrosTests.CadastrarChat(user1, user2, chatService);
            Mensagens mensagem = ModelCadastrosTests.CadastrarMensagem(chat, user1.getIdUsuario(), mensagensService);
            System.out.println(mensagem);
            mensagensService.Deletar(mensagem.getIdMensagens());
            chatService.Deletar(chat);
            usuariosService.Deletar(user1.getIdUsuario());
            usuariosService.Deletar(user2.getIdUsuario());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
