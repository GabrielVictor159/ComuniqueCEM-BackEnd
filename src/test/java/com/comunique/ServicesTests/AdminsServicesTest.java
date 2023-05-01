package com.comunique.ServicesTests;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunique.dto.AdminsDTO;
import com.comunique.functions.AleatoryString;
import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Admins;
import com.comunique.model.Instituicoes;
import com.comunique.service.AdminsService;
import com.comunique.service.InstituicoesService;

@SpringBootTest
public class AdminsServicesTest {

    @Autowired
    AdminsService adminsService;

    @Autowired
    InstituicoesService instituicoesService;

    @Test
    public void CadastrarTest() {
        Assertions.assertEquals("Sucesso", Cadastrar());
    }

    @Test
    public void LoginTest() {
        Assertions.assertEquals("Sucesso", Login());
    }

    public String Cadastrar() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            Admins admin = ModelCadastrosTests.CadastrarAdmin(instituicao, adminsService,
                    AleatoryString.getAlphaNumericString(7));
            adminsService.Deletar(admin.getIdAdmin());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String Login() {
        try {
            Instituicoes instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
            String nome = AleatoryString.getAlphaNumericString(7);
            String senha = AleatoryString.getAlphaNumericString(7);
            AdminsDTO adminDTO = new AdminsDTO(nome, senha);
            Admins admin = new Admins();
            BeanUtils.copyProperties(adminDTO, admin);
            admin.setInstituicao(instituicao);
            adminsService.Cadastrar(admin);
            Optional<Admins> adminLogin = adminsService.Login(nome, senha);
            if (adminLogin.isEmpty()) {
                adminsService.Deletar(admin.getIdAdmin());
                instituicoesService.Deletar(instituicao.getIdInstituicao());
                return "Não foi possivel fazer o login";
            }
            adminsService.Deletar(admin.getIdAdmin());
            instituicoesService.Deletar(instituicao.getIdInstituicao());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
