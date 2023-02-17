package com.comunique.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.comunique.dto.CronogramaDTO;
import com.comunique.model.Cronograma;
import com.comunique.model.Usuarios;
import com.comunique.service.CronogramaService;
import com.comunique.service.UsuariosService;

@RestController
@RequestMapping(value = "/Cronograma", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin
public class CronogramaController {

    @Autowired
    CronogramaService cronogramaService;
    @Autowired
    UsuariosService usuariosService;

    @GetMapping("/{emailUsuario}/{senhaUsuario}/{id}")
    public ResponseEntity<Object> findCronograma(@PathVariable UUID id, @PathVariable String emailUsuario,
            @PathVariable String senhaUsuario) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        Optional<Cronograma> cronograma = cronogramaService.getCronograma(id);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (cronograma.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                cronograma.get().add(
                        linkTo(methodOn(CronogramaController.class).getAll(emailUsuario, senhaUsuario))
                                .withRel("Todos os seus cronogramas"));
                return new ResponseEntity<>(cronograma.get(), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @GetMapping("/getAll/{emailUsuario}/{senhaUsuario}")
    public ResponseEntity<Object> getAll(@PathVariable String emailUsuario, @PathVariable String senhaUsuario) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                List<Cronograma> cronogramas = cronogramaService.getCronogramaUser(usuario.get());
                for (Cronograma cronograma : cronogramas) {
                    cronograma.add(
                            linkTo(methodOn(CronogramaController.class).findCronograma(cronograma.getIdCronograma(),
                                    emailUsuario, senhaUsuario)).withSelfRel());
                }
                return new ResponseEntity<>(cronogramas, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/{emailUsuario}/{senhaUsuario}")
    public ResponseEntity<Object> cadastrarCronograma(@PathVariable String emailUsuario,
            @PathVariable String senhaUsuario, @RequestBody @Valid CronogramaDTO dto) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                Cronograma cronograma = new Cronograma();
                BeanUtils.copyProperties(dto, cronograma);
                cronograma.setUsuario(usuario.get());
                Cronograma cadastro = cronogramaService.Cadastrar(cronograma);
                return new ResponseEntity<>(cadastro, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/{emailUsuario}/{senhaUsuario}/{id}")
    public ResponseEntity<Object> AlterarCronograma(@PathVariable String emailUsuario,
            @PathVariable String senhaUsuario, @PathVariable UUID id, @RequestBody @Valid CronogramaDTO dto) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        Optional<Cronograma> cronograma = cronogramaService.getCronograma(id);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (cronograma.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                BeanUtils.copyProperties(dto, cronograma.get());
                cronograma.get().setUsuario(usuario.get());
                Cronograma cadastro = cronogramaService.Cadastrar(cronograma.get());
                return new ResponseEntity<>(cadastro, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @DeleteMapping("/{emailUsuario}/{senhaUsuario}/{id}")
    public ResponseEntity<Object> DeletarCronograma(@PathVariable String emailUsuario,
            @PathVariable String senhaUsuario, @PathVariable UUID id) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        Optional<Cronograma> cronograma = cronogramaService.getCronograma(id);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (cronograma.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                cronogramaService.Deletar(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @DeleteMapping("/{emailUsuario}/{senhaUsuario}")
    public ResponseEntity<Object> DeletarAllCronograma(@PathVariable String emailUsuario,
            @PathVariable String senhaUsuario) {
        Optional<Usuarios> usuario = usuariosService.Login(emailUsuario, senhaUsuario);
        if (usuario.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                cronogramaService.DeletarAllByUsuario(usuario.get());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

}
