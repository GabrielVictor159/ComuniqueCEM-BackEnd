package com.comunique.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comunique.model.Chat;
import com.comunique.model.Mensagens;
import com.comunique.model.Usuarios;
import com.comunique.service.ChatService;
import com.comunique.service.MensagensService;
import com.comunique.service.UsuariosService;

@RestController
@RequestMapping(value = "/Chat", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin
public class ChatController {

    @Autowired
    ChatService chatService;

    @Autowired
    UsuariosService usuariosService;

    @Autowired
    MensagensService mensagensService;

    @GetMapping("/{emailUsuario}/{senhaUsuario}/{id}")
    public ResponseEntity<Object> getOne(@PathVariable String emailUsuario, @PathVariable String senhaUsuario,
            @PathVariable UUID id) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        Optional<Chat> chat = chatService.getChatById(id);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (chat.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (usuario.get() != chat.get().getUsuario1() && usuario.get() != chat.get().getUsuario2()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                chat.get().add(
                        linkTo(methodOn(ChatController.class).getAll(emailUsuario, senhaUsuario))
                                .withRel("Todos os seus chats"));
                return new ResponseEntity<>(chat.get(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/getAll/{emailUsuario}/{senhaUsuario}")
    public ResponseEntity<Object> getAll(@PathVariable String emailUsuario, @PathVariable String senhaUsuario) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                List<Chat> chats = chatService.getChatByUser(usuario.get());
                List<ChatComMensagem> listChat = new ArrayList<>();
                for (Chat chat : chats) {
                    ChatComMensagem chatComMensagem = new ChatComMensagem();
                    BeanUtils.copyProperties(chat, chatComMensagem);
                    chatComMensagem.add(
                            linkTo(methodOn(ChatController.class).getOne(
                                    emailUsuario, senhaUsuario, chatComMensagem.getIdChat())).withSelfRel());
                    chatComMensagem.setUltimaMensagem(mensagensService.ultimaMensagemChat(chat));
                    listChat.add(chatComMensagem);
                }

                return new ResponseEntity<>(listChat, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/{emailUsuario}/{senhaUsuario}/{idUsuario}")
    public ResponseEntity<Object> Cadastrar(@PathVariable String emailUsuario, @PathVariable String senhaUsuario,
            @PathVariable UUID idUsuario) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        Optional<Usuarios> usuario2 = usuariosService.getUser(idUsuario);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (usuario2.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Optional<Chat> chatTest = chatService.getChatByTwoUser(usuario.get(), usuario2.get());
            if (chatTest.isPresent()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            try {
                Chat chat = new Chat();
                chat.setUsuario1(usuario.get());
                chat.setUsuario2(usuario2.get());
                Chat cadastro = chatService.Cadastrar(chat);
                return new ResponseEntity<>(cadastro, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @DeleteMapping("/{emailUsuario}/{senhaUsuario}/{id}")
    public ResponseEntity<Object> Delete(@PathVariable String emailUsuario, @PathVariable String senhaUsuario,
            @PathVariable UUID id) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        Optional<Chat> chat = chatService.getChatById(id);
        int contador = 0;
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (chat.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Optional<Chat> chatTeste1 = chatService.getChatByTwoUser(usuario.get(), chat.get().getUsuario1());
            Optional<Chat> chatTeste2 = chatService.getChatByTwoUser(usuario.get(), chat.get().getUsuario2());
            if (chatTeste1.isPresent() && contador == 0) {
                if (chatTeste1.get().getIdChat().equals(chat.get().getIdChat())) {
                    try {
                        contador++;
                        mensagensService.DeleteForChat(chat.get());
                        chatService.Deletar(chat.get());
                        return new ResponseEntity<>(HttpStatus.OK);
                    } catch (Exception e) {
                        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
            }
            if (chatTeste2.isPresent() && contador == 0) {
                if (chatTeste2.get().getIdChat().equals(chat.get().getIdChat())) {
                    try {
                        contador++;
                        mensagensService.DeleteForChat(chat.get());
                        chatService.Deletar(chat.get());
                        return new ResponseEntity<>(HttpStatus.OK);
                    } catch (Exception e) {
                        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }
            }
            if (contador == 0) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }

        }

    }

    public class ChatComMensagem extends Chat {

        private Mensagens ultimaMensagem;

        public ChatComMensagem() {
        }

        public Mensagens getUltimaMensagem() {
            return ultimaMensagem;
        }

        public void setUltimaMensagem(Mensagens ultimaMensagem) {
            this.ultimaMensagem = ultimaMensagem;
        }
    }

}
