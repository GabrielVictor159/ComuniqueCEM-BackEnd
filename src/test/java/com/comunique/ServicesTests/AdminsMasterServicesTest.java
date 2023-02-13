package com.comunique.ServicesTests;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.comunique.dto.AdminsMasterDTO;
import com.comunique.functions.AleatoryString;
import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.AdminsMaster;
import com.comunique.service.AdminsMasterService;

@SpringBootTest
public class AdminsMasterServicesTest {

    @Autowired
    AdminsMasterService adminsMasterService;

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
            AdminsMaster admin = ModelCadastrosTests.CadastrarAdminMaster(adminsMasterService);
            adminsMasterService.Deletar(admin.getIdAdmin());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String Login() {
        try {
            String nome = AleatoryString.getAlphaNumericString(7);
            String senha = AleatoryString.getAlphaNumericString(7);
            AdminsMasterDTO adminDTO = new AdminsMasterDTO(nome, senha);
            AdminsMaster admin = new AdminsMaster();
            BeanUtils.copyProperties(adminDTO, admin);
            adminsMasterService.Cadastrar(admin);
            Optional<AdminsMaster> adminLogin = adminsMasterService.Login(nome, senha);
            if (adminLogin.isEmpty()) {
                adminsMasterService.Deletar(admin.getIdAdmin());
                return "Não foi possivel fazer o login";
            }
            adminsMasterService.Deletar(admin.getIdAdmin());
            return "Sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
