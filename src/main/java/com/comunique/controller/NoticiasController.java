package com.comunique.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.comunique.dto.NoticiasDTO;
import com.comunique.model.Noticias;
import com.comunique.service.AdminsService;
import com.comunique.service.NoticiasService;

@RestController
@RequestMapping(value = "/Noticias", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin
public class NoticiasController {

    @Autowired
    NoticiasService noticiasService;
    @Autowired
    AdminsService adminsService;

    @GetMapping("/{index}/{size}")
    public ResponseEntity<Object> getAllNoticiasPage(@PathVariable(value = "index") int page,
            @PathVariable(value = "size") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idNoticia").descending());
        List<Noticias> noticias = noticiasService.getAllNoticiasPageable(pageable);

        for (Noticias noticia : noticias) {
            UUID id = noticia.getIdNoticia();
            noticia.add(linkTo(methodOn(NoticiasController.class).getNoticia(id)).withSelfRel());
        }

        return new ResponseEntity<Object>(noticias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getNoticia(@PathVariable(value = "id") UUID id) {
        Optional<Noticias> noticia = noticiasService.getNoticia(id);
        if (noticia.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            noticia.get().add(linkTo(methodOn(NoticiasController.class).getNoticia(id)).withSelfRel());
            return new ResponseEntity<Object>(noticia.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> GetAllNoticias() {
        List<Noticias> noticiasList = noticiasService.getAllNoticias();
        if (noticiasList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Noticias noticia : noticiasList) {
                UUID id = noticia.getIdNoticia();
                noticia.add(linkTo(methodOn(NoticiasController.class).getNoticia(id)).withSelfRel());
            }
            return new ResponseEntity<Object>(noticiasList, HttpStatus.OK);
        }
    }

    @PostMapping("/{adminNome}/{senhaAdmin}")
    public ResponseEntity<Object> cadastrarQuestion(@PathVariable(value = "adminNome") String adminNome,
            @PathVariable(value = "senhaAdmin") String senhaAdmin, @RequestBody @Valid NoticiasDTO noticiasDto) {
        if (adminsService.Login(adminNome, senhaAdmin).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                Noticias noticia = new Noticias();
                BeanUtils.copyProperties(noticiasDto, noticia);
                noticia.add(
                        linkTo(methodOn(NoticiasController.class).getNoticia(noticia.getIdNoticia())).withSelfRel());
                noticia.add(linkTo(methodOn(NoticiasController.class).getAllNoticiasPage(0, 10))
                        .withRel("Noticias com paginação"));
                noticia.add(linkTo(methodOn(NoticiasController.class).GetAllNoticias()).withRel("todas as noticias"));
                return new ResponseEntity<Object>(noticia, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/{adminNome}/{adminSenha}/{id}")
    public ResponseEntity<Object> updateNoticia(@PathVariable(value = "id") UUID id,
            @PathVariable(value = "adminNome") String adminNome, @PathVariable(value = "adminSenha") String adminSenha,
            @RequestBody @Valid NoticiasDTO noticiasDTO) {
        Optional<Noticias> noticia = noticiasService.getNoticia(id);
        if (adminsService.Login(adminNome, adminSenha).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            if (noticia.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            } else {
                BeanUtils.copyProperties(noticiasDTO, noticia);
                try {
                    Noticias noticiaAlterada = noticiasService.Cadastrar(noticia.get());
                    noticiaAlterada
                            .add(linkTo(methodOn(NoticiasController.class).getNoticia(noticiaAlterada.getIdNoticia()))
                                    .withSelfRel());
                    noticiaAlterada.add(linkTo(methodOn(NoticiasController.class).getAllNoticiasPage(0, 10))
                            .withRel("Noticias com paginação"));
                    noticiaAlterada.add(
                            linkTo(methodOn(NoticiasController.class).GetAllNoticias()).withRel("todas as noticias"));
                    return new ResponseEntity<Object>(noticiaAlterada, HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
    }

    @DeleteMapping("/{adminNome}/{AdminSenha}/{id}")
    public ResponseEntity<Object> deleteNoticia(@PathVariable(value = "id") UUID id,
            @PathVariable(value = "adminNome") String adminNome,
            @PathVariable(value = "adminSenha") String adminSenha) {
        if (adminsService.Login(adminNome, adminSenha).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                noticiasService.Deletar(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

}
