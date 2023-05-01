package com.comunique.ControllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.comunique.controller.UsuariosController;
import com.comunique.dto.InstituicoesDTO;
import com.comunique.dto.UsuariosDTO;
import com.comunique.functions.AleatoryString;
import com.comunique.functions.ModelCadastrosTests;
import com.comunique.functions.Reflections;
import com.comunique.model.Admins;
import com.comunique.model.Instituicoes;
import com.comunique.model.Usuarios;
import com.comunique.model.enums.typeUsuario;
import com.comunique.service.AdminsService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.UsuariosService;
import com.comunique.service.UsuariosSolicitacoesService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuariosControllerTest {
        @Autowired
        UsuariosService usuariosService;
        @Autowired
        InstituicoesService instituicoesService;
        @Autowired
        TestRestTemplate testRestTemplate;
        @Autowired
        AdminsService adminsService;
        @Autowired
        UsuariosSolicitacoesService usuariosSolicitacoesService;
        private Instituicoes instituicao;
        private Usuarios usuario;
        private Admins admin;
        private String senhaAdmin = AleatoryString.getAlphaNumericString(7);
        private String senhaUsuario = AleatoryString.getAlphaNumericString(7);
        private String senhaAluno = AleatoryString.getAlphaNumericString(7);
        private String senhaProfessor = AleatoryString.getAlphaNumericString(7);

        @Before
        public void setUp() {
                InstituicoesDTO dto = new InstituicoesDTO(AleatoryString.getAlphaNumericString(7), senhaAluno,
                                senhaProfessor);
                Instituicoes instituto = new Instituicoes();
                BeanUtils.copyProperties(dto, instituto);
                this.instituicao = instituicoesService.Cadastrar(instituto);
                this.usuario = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, senhaUsuario);
                this.admin = ModelCadastrosTests.CadastrarAdmin(instituicao, adminsService, senhaAdmin);
        }

        @After
        public void setDown() {
                usuariosService.DeletarAllByInstituicao(instituicao);
                adminsService.DeletarAllByInstituicao(instituicao);
                usuariosSolicitacoesService.DeletarAllByInstituicao(instituicao);
                instituicoesService.Deletar(instituicao.getIdInstituicao());
        }

        @Test
        public void LoginTest() throws NoSuchMethodException {
                String URI = Reflections.getURI(UsuariosController.class, "Login");
                ResponseEntity<Object> response = testRestTemplate.exchange(
                                URI,
                                HttpMethod.GET,
                                null,
                                Object.class,
                                usuario.getEmail(),
                                senhaUsuario);
                assertEquals(HttpStatus.OK, response.getStatusCode());
                System.out.println(response.getBody());
                ResponseEntity<Object> response2 = testRestTemplate.exchange(
                                URI,
                                HttpMethod.GET,
                                null,
                                Object.class,
                                usuario.getEmail(),
                                senhaUsuario + "a");
                assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        }

        @Test
        public void getAllUsersInstituto() throws NoSuchMethodException {
                String URI = Reflections.getURI(UsuariosController.class, "getAllUsuariosInstituto");
                ResponseEntity<Object> response = testRestTemplate.exchange(
                                URI,
                                HttpMethod.GET,
                                null,
                                Object.class,
                                usuario.getEmail(),
                                senhaUsuario);
                assertEquals(HttpStatus.OK, response.getStatusCode());
                System.out.println(response.getBody());
                ResponseEntity<Object> response2 = testRestTemplate.exchange(
                                URI,
                                HttpMethod.GET,
                                null,
                                Object.class,
                                usuario.getEmail(),
                                senhaUsuario + "5");
                assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        }

        @Test
        public void getAllUsuariosInstitutoPaginado() throws NoSuchMethodException {
                String URI = Reflections.getURI(UsuariosController.class, "getAllUsuariosInstitutoPaginado");
                ResponseEntity<Object> response1 = testRestTemplate.exchange(
                                URI + "?pagina=0&tamanho=10",
                                HttpMethod.GET,
                                null,
                                Object.class,
                                usuario.getEmail(),
                                senhaUsuario);
                assertEquals(HttpStatus.OK, response1.getStatusCode());
                System.out.println(response1.getBody());
                ResponseEntity<Object> response2 = testRestTemplate.exchange(
                                URI + "?pagina=0&tamanho=10",
                                HttpMethod.GET,
                                null,
                                Object.class,
                                usuario.getEmail(),
                                senhaUsuario + "5");
                assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        }

        @Test
        public void getUsuarioTest() throws NoSuchMethodException {
                String URI = Reflections.getURI(UsuariosController.class, "getUsuario");
                ResponseEntity<Object> response = testRestTemplate.exchange(
                                URI,
                                HttpMethod.GET,
                                null,
                                Object.class,
                                usuario.getIdUsuario());
                assertEquals(HttpStatus.OK, response.getStatusCode());
                UUID idFalso = UUID.randomUUID();
                ResponseEntity<Object> response2 = testRestTemplate.exchange(
                                URI,
                                HttpMethod.GET,
                                null,
                                Object.class,
                                idFalso);
                assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
        }

        @Test
        public void registrarUsuarioTest() throws NoSuchMethodException {
                try {
                        String URI = Reflections.getURI(UsuariosController.class, "registrarUsuario");
                        UsuariosDTO dto = new UsuariosDTO(AleatoryString.getAlphaNumericString(7), typeUsuario.ALUNO,
                                        AleatoryString.getAlphaNumericString(7),
                                        AleatoryString.getAlphaNumericString(7),
                                        AleatoryString.getAlphaNumericString(7), true);
                        ResponseEntity<Object> response = testRestTemplate.postForEntity(
                                        URI,
                                        dto,
                                        Object.class,
                                        instituicao.getNome(),
                                        senhaAluno);
                        assertEquals(HttpStatus.OK, response.getStatusCode());
                        System.out.println("Cadastro aluno: " + response.getBody());
                        ResponseEntity<Object> response2 = testRestTemplate.postForEntity(
                                        URI,
                                        dto,
                                        Object.class,
                                        instituicao.getNome(),
                                        senhaProfessor);
                        assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
                        dto.setTipoUsuario(typeUsuario.PROFESSOR);
                        dto.setEmail(AleatoryString.getAlphaNumericString(7));
                        ResponseEntity<Object> response3 = testRestTemplate.postForEntity(
                                        URI,
                                        dto,
                                        Object.class,
                                        instituicao.getNome(),
                                        senhaProfessor);
                        assertEquals(HttpStatus.OK, response3.getStatusCode());
                        System.out.println("Cadastro professor: " + response3.getBody());
                        ResponseEntity<Object> response4 = testRestTemplate.postForEntity(
                                        URI,
                                        dto,
                                        Object.class,
                                        instituicao.getNome(),
                                        senhaAluno);
                        assertEquals(HttpStatus.UNAUTHORIZED, response4.getStatusCode());
                } catch (Exception e) {
                        System.out.println(e);
                }

        }

        @Test
        public void updateUsuarioTest() throws NoSuchMethodException {
                String URI = Reflections.getURI(UsuariosController.class, "updateUsuario");
                UsuariosDTO dto = new UsuariosDTO(AleatoryString.getAlphaNumericString(7), usuario.getTipoUsuario(),
                                AleatoryString.getAlphaNumericString(7), AleatoryString.getAlphaNumericString(7),
                                AleatoryString.getAlphaNumericString(7), true);
                ResponseEntity<Object> response = testRestTemplate.exchange(
                                URI,
                                HttpMethod.PUT,
                                new HttpEntity<>(dto),
                                Object.class,
                                usuario.getEmail(),
                                senhaUsuario);
                assertEquals(HttpStatus.OK, response.getStatusCode());
                System.out.println(response.getBody());
                ResponseEntity<Object> response2 = testRestTemplate.exchange(
                                URI,
                                HttpMethod.PUT,
                                new HttpEntity<>(dto),
                                Object.class,
                                usuario.getEmail(),
                                senhaUsuario + "5");
                assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        }

        @Test
        public void onlineTest() throws NoSuchMethodException {
                String URI = Reflections.getURI(UsuariosController.class, "Online");
                ResponseEntity<Object> response = testRestTemplate.exchange(
                                URI,
                                HttpMethod.PUT,
                                null,
                                Object.class,
                                usuario.getEmail(),
                                senhaUsuario, false);
                assertEquals(HttpStatus.OK, response.getStatusCode());
                System.out.println(response.getBody());
                ResponseEntity<Object> response2 = testRestTemplate.exchange(
                                URI,
                                HttpMethod.PUT,
                                null,
                                Object.class,
                                usuario.getEmail(),
                                senhaUsuario + "5", false);
                assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
        }

        @Test
        public void deletarTest() throws NoSuchMethodException {
                String URI = Reflections.getURI(UsuariosController.class, "deletarUsuario");
                String senha = "dasdas";
                Usuarios user = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, senha);
                ResponseEntity<Object> response = testRestTemplate.exchange(
                                URI,
                                HttpMethod.DELETE,
                                null,
                                Object.class,
                                user.getEmail(),
                                senha + "5");
                assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
                ResponseEntity<Object> response2 = testRestTemplate.exchange(
                                URI,
                                HttpMethod.DELETE,
                                null,
                                Object.class,
                                user.getEmail(),
                                senha);
                assertEquals(HttpStatus.OK, response2.getStatusCode());
        }

        @Test
        public void adminDeletarTest() throws NoSuchMethodException {
                String URI = Reflections.getURI(UsuariosController.class, "deletarUsuarioAdmin");
                String senha = "dasdas";
                Usuarios user = ModelCadastrosTests.CadastrarUsuario(instituicao, usuariosService, senha);
                ResponseEntity<Object> response = testRestTemplate.exchange(
                                URI,
                                HttpMethod.DELETE,
                                null,
                                Object.class,
                                admin.getNome(),
                                senhaAdmin + "5",
                                user.getIdUsuario());
                assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
                UUID idFalso = UUID.randomUUID();
                ResponseEntity<Object> response2 = testRestTemplate.exchange(
                                URI,
                                HttpMethod.DELETE,
                                null,
                                Object.class,
                                admin.getNome(),
                                senhaAdmin,
                                idFalso);
                assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
                ResponseEntity<Object> response3 = testRestTemplate.exchange(
                                URI,
                                HttpMethod.DELETE,
                                null,
                                Object.class,
                                admin.getNome(),
                                senhaAdmin,
                                user.getIdUsuario());
                assertEquals(HttpStatus.OK, response3.getStatusCode());

        }

        @Test
        public void adminUpdateUsuario() throws NoSuchMethodException {
                String URI = Reflections.getURI(UsuariosController.class, "updateUsuarioAdmin");
                UsuariosDTO dto = new UsuariosDTO(AleatoryString.getAlphaNumericString(7), typeUsuario.PROFESSOR,
                                AleatoryString.getAlphaNumericString(7), AleatoryString.getAlphaNumericString(7),
                                AleatoryString.getAlphaNumericString(7), true);
                ResponseEntity<Object> response = testRestTemplate.exchange(
                                URI,
                                HttpMethod.PUT,
                                new HttpEntity<>(dto),
                                Object.class,
                                admin.getNome(),
                                senhaAdmin,
                                usuario.getIdUsuario());
                assertEquals(HttpStatus.OK, response.getStatusCode());
                System.out.println(response.getBody());
                ResponseEntity<Object> response2 = testRestTemplate.exchange(
                                URI,
                                HttpMethod.PUT,
                                new HttpEntity<>(dto),
                                Object.class,
                                admin.getNome(),
                                senhaAdmin + "5",
                                usuario.getIdUsuario());
                assertEquals(HttpStatus.UNAUTHORIZED, response2.getStatusCode());
                UUID idFalso = UUID.randomUUID();
                ResponseEntity<Object> response3 = testRestTemplate.exchange(
                                URI,
                                HttpMethod.PUT,
                                new HttpEntity<>(dto),
                                Object.class,
                                admin.getNome(),
                                senhaAdmin,
                                idFalso);
                assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());

        }

}
