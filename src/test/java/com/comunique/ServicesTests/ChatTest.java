package com.comunique.ServicesTests;

import com.comunique.dto.ChatDTO;
import com.comunique.dto.UsuariosDTO;
import com.comunique.model.Chat;
import com.comunique.model.Cronograma;
import com.comunique.model.Usuarios;
import com.comunique.repository.ChatRepository;
import com.comunique.service.ChatService;
import com.comunique.service.UsuariosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ChatTest {
    @Autowired
    ChatService chatService;

    @Autowired
    UsuariosService usuariosService;

    @Test
    public void getChatTest(){
        Assertions.assertEquals("Sucesso",GetChatTest());
    }
    @Test
    public void getChatByUserTest(){
        Assertions.assertEquals("Sucesso",GetChatByUserTest());
    }
    public String GetChatByUserTest(){
        try {
            Usuarios user1 = CadastrarUsuario();
            Usuarios user2 = CadastrarUsuario();
            Usuarios user3 = CadastrarUsuario();
            Chat chat = CadastrarChat(user1, user2);
            Chat chat2 = CadastrarChat(user1, user3);
            List<Chat> listChats = chatService.getChatByUser(user1);
            System.out.println(listChats);
            chatService.Deletar(chat);
            chatService.Deletar(chat2);
            usuariosService.Deletar(user1);
            usuariosService.Deletar(user2);
            usuariosService.Deletar(user3);
            return "Sucesso";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
    public String GetChatTest(){
        try{
            Usuarios user1 = CadastrarUsuario();
            Usuarios user2 =CadastrarUsuario();
            Chat chat = CadastrarChat(user1, user2);
            Optional<Chat> chat2 = chatService.getChatById(chat.getIdChat());
            System.out.println(chat2.get());
            chatService.Deletar(chat2.get());
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
