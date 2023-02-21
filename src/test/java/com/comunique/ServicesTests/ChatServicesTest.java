package com.comunique.ServicesTests;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Chat;
import com.comunique.model.Instituicoes;
import com.comunique.model.Usuarios;
import com.comunique.service.ChatService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.UsuariosService;

@SpringBootTest
public class ChatServicesTest {
    @Autowired
    ChatService chatService;

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    InstituicoesService instituicoesService;

    @Test
    public void getChatTest() {
        Assertions.assertEquals("Sucesso", GetChatTest());
    }

    @Test
    public void getChatByUserTest() {
        Assertions.assertEquals("Sucesso", GetChatByUserTest());
    }

    public String GetChatByUserTest() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios user1 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            Usuarios user2 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            Usuarios user3 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            Chat chat = ModelCadastrosTests.CadastrarChat(user1, user2, chatService);
            Chat chat2 = ModelCadastrosTests.CadastrarChat(user1, user3, chatService);
            List<Chat> listChats = chatService.getChatByUser(user1);
            System.out.println(listChats);
            chatService.Deletar(chat);
            chatService.Deletar(chat2);
            usuariosService.Deletar(user1.getIdUsuario());
            usuariosService.Deletar(user2.getIdUsuario());
            usuariosService.Deletar(user3.getIdUsuario());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String GetChatTest() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios user1 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            Usuarios user2 = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            Chat chat = ModelCadastrosTests.CadastrarChat(user1, user2, chatService);
            Optional<Chat> chat2 = chatService.getChatById(chat.getIdChat());
            System.out.println(chat2.get());
            chatService.Deletar(chat2.get());
            usuariosService.Deletar(user1.getIdUsuario());
            usuariosService.Deletar(user2.getIdUsuario());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
