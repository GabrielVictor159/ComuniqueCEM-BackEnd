package com.comunique.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comunique.dto.MensagensDTO;
import com.comunique.model.Chat;
import com.comunique.model.Mensagens;
import com.comunique.model.Usuarios;
import com.comunique.service.ChatService;
import com.comunique.service.MensagensService;
import com.comunique.service.UsuariosService;

@RestController
@RequestMapping(value = "/Mensagens", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin
public class MensagensController {
    @Autowired
    MensagensService mensagensService;
    @Autowired
    UsuariosService usuariosService;
    @Autowired
    ChatService chatService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMessage(@PathVariable UUID id) {
        Optional<Mensagens> mensagem = mensagensService.getMensagem(id);
        if (mensagem.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(mensagem.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllChat/{emailUsuario}/{senhaUsuario}/{id}")
    public ResponseEntity<Object> getAllMessageChat(@PathVariable String emailUsuario,
            @PathVariable String senhaUsuario, @PathVariable UUID id) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        Optional<Chat> chat = chatService.getChatById(id);

        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (chat.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (chat.get().getUsuario1() != usuario.get() && chat.get().getUsuario2() != usuario.get()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                List<Mensagens> mensagens = mensagensService.getAllMensagensForChat(chat.get());
                for (Mensagens mensagem : mensagens) {
                    mensagem.add(
                            linkTo(methodOn(MensagensController.class).getMessage(mensagem.getIdMensagens()))
                                    .withSelfRel());
                }
                return new ResponseEntity<>(mensagens, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/getAllUser/{emailUsuario}/{senhaUsuario}")
    public ResponseEntity<Object> getAllMessageUser(@PathVariable String emailUsuario,
            @PathVariable String senhaUsuario) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                List<Chat> chats = chatService.getChatByUser(usuario.get());
                List<Mensagens> mensagens = new ArrayList<>();
                for (Chat chat : chats) {
                    mensagens.addAll(mensagensService.getAllMensagensForChat(chat));
                }
                for (Mensagens mensagem : mensagens) {
                    mensagem.add(
                            linkTo(methodOn(MensagensController.class).getMessage(mensagem.getIdMensagens()))
                                    .withSelfRel());
                }
                return new ResponseEntity<>(mensagens, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/{emailUsuario}/{senhaUsuario}/{idChat}")
    public ResponseEntity<Object> adicionar(@PathVariable String emailUsuario, @PathVariable String senhaUsuario,
            @PathVariable UUID idChat, @RequestBody @Valid MensagensDTO dto) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        Optional<Chat> chat = chatService.getChatById(idChat);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (chat.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                Mensagens mensagem = new Mensagens();
                BeanUtils.copyProperties(dto, mensagem);
                mensagem.setChat(chat.get());
                Mensagens cadastro = mensagensService.Cadastrar(mensagem);
                return new ResponseEntity<>(cadastro, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/confirmarEntrega/{emailUsuario}/{senhaUsuario}/{id}")
    public ResponseEntity<Object> confirmarEntrega(@PathVariable String emailUsuario, @PathVariable String senhaUsuario,
            @PathVariable UUID id) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        Optional<Mensagens> mensagem = mensagensService.getMensagem(id);

        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (mensagem.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (usuario.get().getIdUsuario() == mensagem.get().getUsuarioEnviou()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (usuario.get() != mensagem.get().getChat().getUsuario1()
                && usuario.get() != mensagem.get().getChat().getUsuario2()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                mensagem.get().setEntregue(true);
                Mensagens cadastro = mensagensService.Cadastrar(mensagem.get());
                return new ResponseEntity<>(cadastro, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/confirmarLida/{emailUsuario}/{senhaUsuario}/{id}")
    public ResponseEntity<Object> confirmarLida(@PathVariable String emailUsuario, @PathVariable String senhaUsuario,
            @PathVariable UUID id) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        Optional<Mensagens> mensagem = mensagensService.getMensagem(id);

        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (mensagem.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (usuario.get().getIdUsuario() == mensagem.get().getUsuarioEnviou()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (usuario.get() != mensagem.get().getChat().getUsuario1()
                && usuario.get() != mensagem.get().getChat().getUsuario2()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                mensagem.get().setLida(true);
                Mensagens cadastro = mensagensService.Cadastrar(mensagem.get());
                return new ResponseEntity<>(cadastro, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/confirmarLidaChat/{emailUsuario}/{senhaUsuario}/{id}")
    public ResponseEntity<Object> confirmarLidaChat(@PathVariable String emailUsuario,
            @PathVariable String senhaUsuario,
            @PathVariable UUID id) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        Optional<Chat> chat = chatService.getChatById(id);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (chat.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (chat.get().getUsuario1() != usuario.get() && chat.get().getUsuario2() != usuario.get()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {

                mensagensService.confirmarLidaChat(chat.get(), usuario.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @PostMapping("/DeletarPedido")
    public ResponseEntity<Object> DeletarPedido(@RequestBody @Valid Mensagens mensagem) {
        try {
            mensagem.setDeletada(true);
            mensagem.setEntregue(false);
            mensagem.setLida(false);
            Mensagens cadastro = mensagensService.Cadastrar(mensagem);
            return new ResponseEntity<>(cadastro, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/DeletarConfirmar/{id}")
    public ResponseEntity<Object> DeletarConfirmar(@PathVariable UUID id) {
        Optional<Mensagens> mensagem = mensagensService.getMensagem(id);
        if (mensagem.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                mensagensService.Deletar(mensagem.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}