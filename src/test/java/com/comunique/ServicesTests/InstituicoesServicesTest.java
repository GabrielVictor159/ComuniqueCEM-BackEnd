package com.comunique.ServicesTests;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunique.dto.InstituicoesDTO;
import com.comunique.functions.AleatoryString;
import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Instituicoes;
import com.comunique.service.InstituicoesService;

@SpringBootTest
public class InstituicoesServicesTest {
    @Autowired
    InstituicoesService instituicoesService;

    @Test
    public void testCadastro() {
        Assertions.assertEquals("Sucesso", cadastrarTest());
    }

    @Test
    public void loginUsuarioTet() {
        Assertions.assertEquals("Sucesso", loginUsuario());
    }

    @Test
    public void loginProfessorTet() {
        Assertions.assertEquals("Sucesso", loginProfessores());
    }

    public String cadastrarTest() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String loginUsuario() {
        try {
            String senha = "4645";
            Instituicoes instituicao = CadastrarInstituicao(senha, AleatoryString.getAlphaNumericString(7));
            Optional<Instituicoes> test = instituicoesService.LoginUsuario(instituicao.getNome(),
                    senha);
            if (test.isEmpty()) {
                return "Não foi possivel fazer o login na instituição";
            }
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String loginProfessores() {
        try {
            String senhaProfessor = "4645";
            Instituicoes instituicao = CadastrarInstituicao("56456", senhaProfessor);
            Optional<Instituicoes> test = instituicoesService.LoginProfessores(instituicao.getNome(),
                    senhaProfessor);
            if (test.isEmpty()) {
                return "Não foi possivel fazer o login na instituição";
            }
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Instituicoes CadastrarInstituicao(String senha, String senhaProfessor) {
        InstituicoesDTO dto = new InstituicoesDTO(AleatoryString.getAlphaNumericString(7), senha, senhaProfessor);
        Instituicoes instituto = new Instituicoes();
        BeanUtils.copyProperties(dto, instituto);
        return instituicoesService.Cadastrar(instituto);
    }
}
