package com.comunique.ControllerTests;

import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.comunique.functions.AleatoryString;
import com.comunique.functions.ModelCadastrosTests;
import com.comunique.model.Cronograma;
import com.comunique.model.Instituicoes;
import com.comunique.model.Usuarios;
import com.comunique.service.CronogramaService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.UsuariosService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CronogramaControllerTest {

    @Autowired
    CronogramaService cronogramaService;
    @Autowired
    UsuariosService usuariosService;
    @Autowired
    InstituicoesService instituicoesService;

    @Autowired
    TestRestTemplate testRestTemplate;

    private Instituicoes instituicao;
    private Usuarios usuario;
    private Cronograma cronograma;
    private String senhaUsuario = AleatoryString.getAlphaNumericString(7);

    @Before
    public void setUp() throws ParseException {
        this.instituicao = ModelCadastrosTests.CadastarInstituicoes(instituicoesService);
        this.usuario = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, senhaUsuario);
        this.cronograma = ModelCadastrosTests.CadastrarCronograma(usuario, cronogramaService);
    }

    @After
    public void setDown() {
        cronogramaService.DeletarAllByUsuario(usuario);
        usuariosService.Deletar(usuario.getIdUsuario());
        instituicoesService.Deletar(instituicao.getIdInstituicao());
    }

}
