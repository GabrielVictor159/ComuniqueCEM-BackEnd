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
import com.comunique.model.Admins;
import com.comunique.model.Instituicoes;
import com.comunique.model.Noticias;
import com.comunique.service.AdminsService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.NoticiasService;

@RestController
@RequestMapping(value = "/Noticias", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin
public class NoticiasController {

    @Autowired
    NoticiasService noticiasService;
    @Autowired
    AdminsService adminsService;
    @Autowired
    InstituicoesService instituicoesService;

    @GetMapping("/{idInstituicao}/{index}/{size}")
    public ResponseEntity<Object> getAllNoticiasPage(@PathVariable(value = "index") int page,
            @PathVariable(value = "size") int size, @PathVariable(value = "idInstituicao") UUID idInstituicao) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idNoticia").descending());
        Optional<Instituicoes> instituicao = instituicoesService.getInstituicao(idInstituicao);
        if (instituicao.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Noticias> noticias = noticiasService.getAllNoticiasPageable(instituicao.get(), pageable);

            for (Noticias noticia : noticias) {
                UUID id = noticia.getIdNoticia();
                noticia.add(linkTo(methodOn(NoticiasController.class).getNoticia(id)).withSelfRel());
            }

            return new ResponseEntity<Object>(noticias, HttpStatus.OK);
        }
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

    @GetMapping("/getAll/{idInstituicao}")
    public ResponseEntity<Object> GetAllNoticias(@PathVariable(value = "idInstituicao") UUID idInstituicao) {
        Optional<Instituicoes> instituicao = instituicoesService.getInstituicao(idInstituicao);
        if (instituicao.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Noticias> noticiasList = noticiasService.getAllNoticiasInsituto(instituicao.get());

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
    }

    @PostMapping("/{adminNome}/{senhaAdmin}")
    public ResponseEntity<Object> cadastrarNoticia(@PathVariable(value = "adminNome") String adminNome,
            @PathVariable(value = "senhaAdmin") String senhaAdmin, @RequestBody @Valid NoticiasDTO noticiasDto) {
        Optional<Admins> admin = adminsService.Login(adminNome, senhaAdmin);
        if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {

            try {
                Noticias noticia = new Noticias();
                BeanUtils.copyProperties(noticiasDto, noticia);
                noticia.setInstituicao(admin.get().getInstituicao());
                Noticias noticiaFinal = noticiasService.Cadastrar(noticia);
                noticiaFinal.add(
                        linkTo(methodOn(NoticiasController.class).getNoticia(noticia.getIdNoticia())).withSelfRel());
                noticiaFinal.add(
                        linkTo(methodOn(NoticiasController.class)
                                .GetAllNoticias(admin.get().getInstituicao().getIdInstituicao()))
                                .withRel("todas as noticias"));
                return new ResponseEntity<Object>(noticiaFinal, HttpStatus.OK);
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
        Optional<Admins> admin = adminsService.Login(adminNome, adminSenha);
        if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            if (noticia.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            } else {
                if (admin.get().getInstituicao() != noticia.get().getInstituicao()) {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
                BeanUtils.copyProperties(noticiasDTO, noticia);
                try {
                    Noticias noticiaAlterada = noticiasService.Cadastrar(noticia.get());
                    noticiaAlterada
                            .add(linkTo(methodOn(NoticiasController.class).getNoticia(noticiaAlterada.getIdNoticia()))
                                    .withSelfRel());

                    noticiaAlterada.add(
                            linkTo(methodOn(NoticiasController.class)
                                    .GetAllNoticias(admin.get().getInstituicao().getIdInstituicao()))
                                    .withRel("todas as noticias"));
                    return new ResponseEntity<Object>(noticiaAlterada, HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
    }

    @DeleteMapping("/{adminNome}/{adminSenha}/{id}")
    public ResponseEntity<Object> deleteNoticia(@PathVariable(value = "id") UUID id,
            @PathVariable(value = "adminNome") String adminNome,
            @PathVariable(value = "adminSenha") String adminSenha) {
        Optional<Noticias> noticia = noticiasService.getNoticia(id);
        Optional<Admins> admin = adminsService.Login(adminNome, adminSenha);
        if (noticia.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (admin.get().getInstituicao() != noticia.get().getInstituicao()) {
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
