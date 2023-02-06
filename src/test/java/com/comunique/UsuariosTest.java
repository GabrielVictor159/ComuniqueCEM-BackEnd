package com.comunique;

import com.comunique.dto.UsuariosDTO;
import com.comunique.model.Usuarios;
import com.comunique.service.UsuariosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.CDATASection;

@SpringBootTest
public class UsuariosTest {
    @Autowired
    UsuariosService usuariosService;
    @Test
    public void cadastrar(){
      Assertions.assertEquals("Sucesso Registrar e deletar usuario", cadastrarExcluir());

    }
    @Test
    public void getUser(){
        Assertions.assertEquals("Sucesso get user", GetUser());
    }

    public String cadastrarExcluir(){
        try{
            UsuariosDTO dto = new UsuariosDTO("test", "test","test","test","test","test",true);
            Usuarios usuario = new Usuarios();
            BeanUtils.copyProperties(dto,usuario);
            Usuarios cadastro = usuariosService.Cadastrar(usuario);
            usuariosService.Deletar(cadastro);
            return "Sucesso Registrar e deletar usuario";
        }
        catch (Exception e){
            return  e.getMessage();
        }
    }
    public String GetUser(){
        try {
            UsuariosDTO dto = new UsuariosDTO("test", "test","test1","test","test","test",true);
            Usuarios usuario = new Usuarios();
            BeanUtils.copyProperties(dto,usuario);
            Usuarios a = usuariosService.Cadastrar(usuario);
            usuariosService.getUser(a.getIdUsuario());
            usuariosService.Deletar(a);
            return "Sucesso get user";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }


}
