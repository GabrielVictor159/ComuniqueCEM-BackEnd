package com.comunique.ServicesTests;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunique.dto.UsuariosDTO;
import com.comunique.functions.AleatoryString;
import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Instituicoes;
import com.comunique.model.Usuarios;
import com.comunique.model.enums.typeUsuario;
import com.comunique.service.InstituicoesService;
import com.comunique.service.UsuariosService;

@SpringBootTest
public class UsuariosServicesTest {

    @Autowired
    UsuariosService usuariosService;
    @Autowired
    InstituicoesService instituicoesService;

    @Test
    public void cadastrar() {
        Assertions.assertEquals("Sucesso Registrar e deletar usuario", cadastrarExcluir());

    }

    @Test
    public void getUser() {
        Assertions.assertEquals("Sucesso get user", GetUser());
    }

    @Test
    public void LoginTest() {
        Assertions.assertEquals("Sucesso", Login());
    }

    @Test
    public void updateUser() {
        Assertions.assertEquals("Sucesso", UpdateUser());
    }

    public String cadastrarExcluir() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios usuario = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            usuariosService.Deletar(usuario.getIdUsuario());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso Registrar e deletar usuario";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String GetUser() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios usuario = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            Optional<Usuarios> usuarioBusca = usuariosService.getUser(usuario.getIdUsuario());
            if (usuarioBusca.isEmpty()) {
                usuariosService.Deletar(usuario.getIdUsuario());
                instituicoesService.Deletar(instituicao.getIdInstituicao());
                return "Não foi possivel localizar o usuario";
            }
            usuariosService.Deletar(usuario.getIdUsuario());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso get user";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String UpdateUser() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios usuario = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, "6515");
            usuario.setEmail("reter");
            usuario.setNomeUsuario("kghjjgh");
            usuario.setTipoUsuario(typeUsuario.PROFESSOR);
            usuario.setUsuarioOnline(false);
            usuario.setSenha("regerger");
            usuariosService.Cadastrar(usuario);
            usuariosService.Deletar(usuario.getIdUsuario());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String Login() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            UsuariosDTO userDto = new UsuariosDTO(AleatoryString.getAlphaNumericString(7),
                    typeUsuario.ALUNO, AleatoryString.getAlphaNumericString(7),
                    AleatoryString.getAlphaNumericString(7),
                    AleatoryString.getAlphaNumericString(7), true);
            Usuarios user = new Usuarios();
            BeanUtils.copyProperties(userDto, user);
            user.setInstituicao(instituicao);
            usuariosService.Cadastrar(user);
            Optional<Usuarios> usuarioLogin = usuariosService.Login(user.getEmail(), userDto.getSenha());
            if (usuarioLogin.isEmpty()) {
                usuariosService.Deletar(user.getIdUsuario());
                instituicoesService.Deletar(instituicao.getIdInstituicao());
                return "Não foi possivel fazer o login";
            }
            usuariosService.Deletar(user.getIdUsuario());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
