package com.comunique.ServicesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Instituicoes;
import com.comunique.model.Usuarios;
import com.comunique.service.InstituicoesService;
import com.comunique.service.UsuariosService;

@SpringBootTest
public class UsuariosTest {

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
    public void updateUser() {
        Assertions.assertEquals("Sucesso", UpdateUser());
    }

    public String cadastrarExcluir() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios usuario = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService);
            usuariosService.Deletar(usuario);
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso Registrar e deletar usuario";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String GetUser() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios usuario = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService);
            usuariosService.getUser(usuario.getIdUsuario());
            usuariosService.Deletar(usuario);
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso get user";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String UpdateUser() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Usuarios usuario = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService);
            usuario.setEmail("reter");
            usuario.setNomeUsuario("kghjjgh");
            usuario.setFotoBackground("ytyj");
            usuario.setTipoUsuario("fadfas");
            usuario.setUsuarioOnline(false);
            usuario.setSenha("regerger");
            usuariosService.Cadastrar(usuario);
            usuariosService.Deletar(usuario);
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
