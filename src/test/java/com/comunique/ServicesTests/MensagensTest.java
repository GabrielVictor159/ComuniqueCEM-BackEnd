package com.comunique.ServicesTests;

import com.comunique.dto.ChatDTO;
import com.comunique.dto.MensagensDTO;
import com.comunique.dto.UsuariosDTO;
import com.comunique.model.Chat;
import com.comunique.model.Mensagens;
import com.comunique.model.Usuarios;
import com.comunique.service.ChatService;
import com.comunique.service.MensagensService;
import com.comunique.service.UsuariosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class MensagensTest {

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    ChatService chatService;

    @Autowired
    MensagensService mensagensService;

    @Test
    public void cadastrarMensagemTest(){
        Assertions.assertEquals("Sucesso",CadastrarMensagemTest());
    }
    @Test
    public void getAllMensagensForChatTest(){
        Assertions.assertEquals("Sucesso",GetAllMensagensForChatTest());
    }
    @Test
    public void deleteInMensagensTest(){
        Assertions.assertEquals("Sucesso",DeleteInMensagensTest());
    }
    public String DeleteInMensagensTest(){
        try {
            Usuarios user1 = CadastrarUsuario();
            Usuarios user2 = CadastrarUsuario();
            Chat chat = CadastrarChat(user1, user2);
            Mensagens mensagem1 = CadastrarMensagem(chat, user1.getIdUsuario());
            Mensagens mensagem2 = CadastrarMensagem(chat,user2.getIdUsuario());
            List<UUID> idMensagens = new ArrayList<>();
            idMensagens.add(mensagem1.getIdMensagens());
            idMensagens.add(mensagem2.getIdMensagens());
            System.out.println(idMensagens);
            mensagensService.DeleteIn(idMensagens);
            chatService.Deletar(chat);
            usuariosService.Deletar(user1);
            usuariosService.Deletar(user2);
            return "Sucesso";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
    public String GetAllMensagensForChatTest(){
        try {
            Usuarios user1 = CadastrarUsuario();
            Usuarios user2 = CadastrarUsuario();
            Chat chat = CadastrarChat(user1, user2);
            Mensagens mensagem1 = CadastrarMensagem(chat, user1.getIdUsuario());
            Mensagens mensagem2 = CadastrarMensagem(chat,user2.getIdUsuario());
            List<Mensagens> test= mensagensService.getAllMensagensForChat(chat);
            System.out.println(test);
            mensagensService.DeleteForChat(chat);
            chatService.Deletar(chat);
            usuariosService.Deletar(user1);
            usuariosService.Deletar(user2);
            return "Sucesso";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    public String CadastrarMensagemTest(){
        try {
            Usuarios user1 = CadastrarUsuario();
            Usuarios user2 = CadastrarUsuario();
            Chat chat = CadastrarChat(user1, user2);
            Mensagens mensagem = CadastrarMensagem(chat, user1.getIdUsuario());
            System.out.println(mensagem);
            mensagensService.Deletar(mensagem.getIdMensagens());
            chatService.Deletar(chat);
            usuariosService.Deletar(user1);
            usuariosService.Deletar(user2);
            return "Sucesso";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
    public Usuarios CadastrarUsuario(){
        UsuariosDTO userDto = new UsuariosDTO(getAlphaNumericString(7), getAlphaNumericString(7),getAlphaNumericString(7),getAlphaNumericString(7),getAlphaNumericString(7),getAlphaNumericString(7),true);
        Usuarios user = new Usuarios();
        BeanUtils.copyProperties(userDto, user);
        return   usuariosService.Cadastrar(user);
    }

    public Chat CadastrarChat(Usuarios user1, Usuarios user2){
        ChatDTO dto = new ChatDTO(user1, user2);
        Chat chat = new Chat();
        BeanUtils.copyProperties(dto, chat);
        return chatService.Cadastrar(chat);

    }
    public Mensagens CadastrarMensagem(Chat chat, UUID idUsuario){
        MensagensDTO mensagemDto = new MensagensDTO(idUsuario,getAlphaNumericString(7),chat);
        Mensagens mensagem = new Mensagens();
        BeanUtils.copyProperties(mensagemDto, mensagem);
        return mensagensService.Cadastrar(mensagem);

    }
    public String getAlphaNumericString(int n)
    {

        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
