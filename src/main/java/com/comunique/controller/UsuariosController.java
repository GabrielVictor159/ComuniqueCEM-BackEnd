package com.comunique.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comunique.dto.UsuariosDTO;
import com.comunique.model.Admins;
import com.comunique.model.Chat;
import com.comunique.model.Instituicoes;
import com.comunique.model.Usuarios;
import com.comunique.model.enums.typeUsuario;
import com.comunique.service.AdminsService;
import com.comunique.service.ChatService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.MensagensService;
import com.comunique.service.UsuariosService;

@RestController
@RequestMapping(value = "/Usuarios", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin
public class UsuariosController {

    @Autowired
    UsuariosService usuariosService;
    @Autowired
    InstituicoesService instituicoesService;
    @Autowired
    ChatService chatService;
    @Autowired
    MensagensService mensagensService;
    @Autowired
    AdminsService adminsService;

    @GetMapping("/{email}/{senha}")
    public ResponseEntity<Usuarios> Login(@PathVariable(value = "email") String email,
            @PathVariable(value = "senha") String senha) {
        Optional<Usuarios> usuarioLogin = usuariosService.Login(email, senha);
        if (usuarioLogin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<Usuarios>(usuarioLogin.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/getAllUsersIntituto/{emailUsuario}/{senhaUsuario}")
    public ResponseEntity<Object> getAllUsuariosInstituto(@PathVariable(value = "emailUsuario") String email,
            @PathVariable(value = "senhaUsuario") String senha) {
        Optional<Usuarios> usuarioLogin = usuariosService.Login(email, senha);
        if (usuarioLogin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        } else {
            return new ResponseEntity<>(usuariosService.getAllUsuariosInstituicao(usuarioLogin.get().getInstituicao()),
                    HttpStatus.OK);

        }
    }

    @GetMapping("/getAllUsuariosInstitutoPaginado/{emailUsuario}/{senhaUsuario}")
    public ResponseEntity<Object> getAllUsuariosInstitutoPaginado(
            @PathVariable(value = "emailUsuario") String email,
            @PathVariable(value = "senhaUsuario") String senha,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho) {
        Optional<Usuarios> usuarioLogin = usuariosService.Login(email, senha);
        if (usuarioLogin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            List<Usuarios> usuarios = usuariosService.getAllUsuariosInstituicaoPaginado(
                    usuarioLogin.get().getInstituicao(), PageRequest.of(pagina, tamanho));
            return new ResponseEntity<>(usuarios, HttpStatus.OK);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> getUsuario(@PathVariable(value = "id") UUID id) {
        Optional<Usuarios> usuario = usuariosService.getUser(id);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<Usuarios>(usuario.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/{nomeInstituicao}/{senhaInstituicao}")
    public ResponseEntity<Object> registrarUsuario(@RequestBody @Valid UsuariosDTO usuarioDto,
            @PathVariable String nomeInstituicao,
            @PathVariable String senhaInstituicao) {
        if (usuarioDto.getTipoUsuario() == typeUsuario.ALUNO) {
            Optional<Instituicoes> testeInstituicao = instituicoesService.LoginUsuario(nomeInstituicao,
                    senhaInstituicao);
            if (testeInstituicao.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                try {
                    Usuarios usuario = new Usuarios();
                    BeanUtils.copyProperties(usuarioDto, usuario);
                    usuario.setInstituicao(testeInstituicao.get());
                    Usuarios cadastro = usuariosService.Cadastrar(usuario);
                    return new ResponseEntity<>(cadastro, HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        } else if (usuarioDto.getTipoUsuario() == typeUsuario.PROFESSOR) {
            Optional<Instituicoes> testeInstituicao = instituicoesService.LoginProfessores(nomeInstituicao,
                    senhaInstituicao);
            if (testeInstituicao.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                try {
                    Usuarios usuario = new Usuarios();
                    BeanUtils.copyProperties(usuarioDto, usuario);
                    usuario.setInstituicao(testeInstituicao.get());
                    Usuarios cadastro = usuariosService.Cadastrar(usuario);
                    return new ResponseEntity<>(cadastro, HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        } else {
            String error = "Tipo de usuario invalido";
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{emailUsuario}/{senhaUsuario}")
    public ResponseEntity<Object> updateUsuario(@RequestBody @Valid UsuariosDTO dto,
            @PathVariable(value = "emailUsuario") String emailUsuario,
            @PathVariable(value = "senhaUsuario") String senhaUsuario) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                dto.setTipoUsuario(usuario.get().getTipoUsuario());
                BeanUtils.copyProperties(usuario.get(), dto);
                Usuarios user = usuariosService.Cadastrar(usuario.get());
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/admin/{nomeAdmin}/{senhaAdmin}/{id}")
    public ResponseEntity<Object> updateUsuarioAdmin(@RequestBody @Valid UsuariosDTO dto,
            @PathVariable String nomeAdmin, @PathVariable String senhaAdmin,
            @PathVariable UUID id) {
        Optional<Admins> admin = adminsService.Login(nomeAdmin, senhaAdmin);
        Optional<Usuarios> usuario = usuariosService.getUser(id);
        if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (admin.get().getInstituicao() != usuario.get().getInstituicao()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                try {
                    BeanUtils.copyProperties(usuario.get(), dto);
                    Usuarios user = usuariosService.Cadastrar(usuario.get());
                    return new ResponseEntity<>(user, HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        }
    }

    @PutMapping("/online/{emailUsuario}/{senhaUsuario}/{online}")
    public ResponseEntity<Object> Online(@PathVariable boolean online,
            @PathVariable(value = "emailUsuario") String emailUsuario,
            @PathVariable(value = "senhaUsuario") String senhaUsuario) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                usuario.get().setUsuarioOnline(online);
                usuariosService.Cadastrar(usuario.get());
                return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping("/{emailUsuario}/{senhaUsuario}")

    public ResponseEntity<Object> deletarUsuario(@PathVariable(value = "emailUsuario") String emailUsuario,
            @PathVariable(value = "senhaUsuario") String senhaUsuario) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                List<Chat> chatsUsuario = chatService.getChatByUser(usuario.get());
                for (Chat chat : chatsUsuario) {
                    mensagensService.DeleteForChat(chat);
                }
                chatService.DeletarAllByUsuario(usuario.get());
                usuariosService.Deletar(usuario.get().getIdUsuario());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping("/admin/{nomeAdmin}/{senhaAdmin}/{id}")
    public ResponseEntity<Object> deletarUsuarioAdmin(@PathVariable String nomeAdmin, @PathVariable String senhaAdmin,
            @PathVariable UUID id) {
        Optional<Admins> admin = adminsService.Login(nomeAdmin, senhaAdmin);
        Optional<Usuarios> usuario = usuariosService.getUser(id);
        if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (admin.get().getInstituicao() != usuario.get().getInstituicao()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                try {
                    List<Chat> chatsUsuario = chatService.getChatByUser(usuario.get());
                    for (Chat chat : chatsUsuario) {
                        mensagensService.DeleteForChat(chat);
                    }
                    chatService.DeletarAllByUsuario(usuario.get());
                    usuariosService.Deletar(usuario.get().getIdUsuario());
                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        }
    }
}
