package com.comunique.ServicesTests;

import com.comunique.dto.CronogramaDTO;
import com.comunique.dto.UsuariosDTO;
import com.comunique.model.Cronograma;
import com.comunique.model.Usuarios;
import com.comunique.service.CronogramaService;
import com.comunique.service.UsuariosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CronogramaTest {
    @Autowired
    CronogramaService cronogramaService;
    @Autowired
    UsuariosService usuariosService;

    @Test
    public void testCadastro(){
        Assertions.assertEquals("Sucesso",Cadastro());
    }
    @Test
    public void testGetCronograma(){
        Assertions.assertEquals("Sucesso",GetCronograma());
    }
    @Test
    public void testGetUserCronograma(){
        Assertions.assertEquals("Sucesso",GetUserCronogramas());
    }
    public String Cadastro(){
        try {
            Usuarios user = CadastrarUsuario();
            Cronograma cronograma = CadastrarCronograma(user);
            cronogramaService.Deletar(cronograma.getIdCronograma());
            usuariosService.Deletar(user);
            return "Sucesso";
        }
        catch (Exception e){
            return e.getMessage();
        }

    }
    public String GetCronograma(){
        try {
            Usuarios user = CadastrarUsuario();
            Cronograma cronograma = CadastrarCronograma(user);
            Optional<Cronograma> test = cronogramaService.getCronograma(cronograma.getIdCronograma());
            cronogramaService.Deletar(cronograma.getIdCronograma());
            usuariosService.Deletar(user);
            return "Sucesso";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
    public String GetUserCronogramas(){
        try {
            Usuarios user = CadastrarUsuario();
            Cronograma cronograma = CadastrarCronograma(user);
            List<Cronograma> list = cronogramaService.getCronogramaUser(user);
            cronogramaService.Deletar(cronograma.getIdCronograma());
            usuariosService.Deletar(user);
            return "Sucesso";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
    public Usuarios CadastrarUsuario(){
        UsuariosDTO userDto = new UsuariosDTO("test", "test","test","test","test","test",true);
        Usuarios user = new Usuarios();
        BeanUtils.copyProperties(userDto, user);
        return   usuariosService.Cadastrar(user);
    }
    public Cronograma CadastrarCronograma(Usuarios user) throws ParseException {
        String dateString = "2023-02-09";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        CronogramaDTO cronogramaDto = new CronogramaDTO(date,"blue",30,"dasdas",user);
        Cronograma cronograma = new Cronograma();
        BeanUtils.copyProperties(cronogramaDto, cronograma);
        return cronogramaService.Cadastrar(cronograma);
    }

}
