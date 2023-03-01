package com.comunique.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.comunique.dto.MensagensDTO;
import com.comunique.model.Admins;
import com.comunique.model.AdminsMaster;
import com.comunique.model.Chat;
import com.comunique.model.Mensagens;
import com.comunique.model.Usuarios;
import com.comunique.service.AdminsMasterService;
import com.comunique.service.AdminsService;
import com.comunique.service.ChatService;
import com.comunique.service.ImageService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.MensagensService;
import com.comunique.service.UsuariosService;

@RestController
@RequestMapping(value = "/Images")
@CrossOrigin
public class ImageController {

    @Autowired
    AdminsService adminsService;
    @Autowired
    ImageService imageService;
    @Autowired
    InstituicoesService instituicoesService;
    @Autowired
    AdminsMasterService adminsMasterService;
    @Autowired
    UsuariosService usuariosService;
    @Autowired
    ChatService chatService;
    @Autowired
    MensagensService mensagensService;

    private static String GlobalPath = "src/main/resources/static/images/";

    public static String removeSpecialCharacters(String email) {
        return email.replaceAll("[^a-zA-Z0-9]+", "");
    }
    
    @PostMapping("/mensagens/{usuarioNome}/{usuarioSenha}/{idChat}/{mensagem}")
    public ResponseEntity<Object> uploadChatImage(@RequestParam("image") MultipartFile image,
            @PathVariable String usuarioNome,
            @PathVariable String usuarioSenha,
            @PathVariable UUID idChat,
            @PathVariable String mensagem) {

        Optional<Usuarios> usuarioLogin = usuariosService.Login(usuarioNome, usuarioSenha);
        Optional<Chat> chat = chatService.getChatById(idChat);
        if (usuarioLogin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (chat.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (usuarioLogin.get().getIdUsuario() != chat.get().getUsuario1().getIdUsuario()
                    && usuarioLogin.get().getIdUsuario() != chat.get().getUsuario2().getIdUsuario()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                try {
                    String decodedMessage = URLDecoder.decode(mensagem, StandardCharsets.UTF_8.toString());
                    imageService.persistir(image,
                    GlobalPath + usuarioLogin.get().getInstituicao().getNome() + "/"
                    +removeSpecialCharacters(chat.get().getIdChat().toString())+ "/"
                    );
                    MensagensDTO dto = new MensagensDTO();
                    dto.setIsfile(true);
                    dto.setEntregue(false);
                    dto.setLida(false);
                    dto.setMensagem("§" + usuarioLogin.get().getInstituicao().getNome() + "/"
                    +removeSpecialCharacters(chat.get().getIdChat().toString())+ "/"+image.getOriginalFilename() + "§" + decodedMessage);
                    dto.setUsuarioEnviou(usuarioLogin.get().getIdUsuario());
                    Mensagens novaMensagem = new Mensagens();
                    BeanUtils.copyProperties(dto, novaMensagem);
                    novaMensagem.setChat(chat.get());
                    mensagensService.Cadastrar(novaMensagem);
                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (IOException e) {
                    return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
    }

    @PutMapping("/usuarioImagePerfil/{login}/{senha}")
    public ResponseEntity<Object> updateImagePeril(@RequestParam("image") MultipartFile image,
            @PathVariable String login, @PathVariable String senha) {
        Optional<Usuarios> usuario = usuariosService.Login(login, senha);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                imageService.persistir(image,
                        GlobalPath + usuario.get().getInstituicao().getNome() + "/"+ removeSpecialCharacters(usuario.get().getEmail()) + "/");
                try {
                    if (usuario.get().getFotoPerfil() != "userIcon.png") {
                        imageService.excluir( GlobalPath + usuario.get().getFotoPerfil());
                    }
                } catch (Exception e) {

                }
                usuario.get().setFotoPerfil(
                        usuario.get().getInstituicao().getNome() + "/" + removeSpecialCharacters(usuario.get().getEmail()) + "/"
                                + image.getOriginalFilename());
                usuariosService.Cadastrar(usuario.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/usuarioImageBackground/{login}/{senha}")
    public ResponseEntity<Object> updateImageBackground(@RequestParam("image") MultipartFile image,
            @PathVariable String login, @PathVariable String senha) {
        Optional<Usuarios> usuario = usuariosService.Login(login, senha);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {

                imageService.persistir(image,
                GlobalPath + usuario.get().getInstituicao().getNome() + "/"+ removeSpecialCharacters(usuario.get().getEmail()) + "/");
                try {
                    if (usuario.get().getFotoBackground() != "background.png") {
                        imageService.excluir( GlobalPath + usuario.get().getFotoBackground());
                    }
                } catch (Exception e) {

                }
                usuario.get().setFotoBackground(
                    usuario.get().getInstituicao().getNome() + "/" + removeSpecialCharacters(usuario.get().getEmail()) + "/"
                    + image.getOriginalFilename());
                usuariosService.Cadastrar(usuario.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}
