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

import com.comunique.dto.QuestoesDTO;
import com.comunique.model.Admins;
import com.comunique.model.Instituicoes;
import com.comunique.model.Questoes;
import com.comunique.service.AdminsService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.QuestoesService;

@RestController
@RequestMapping(value = "/Questoes", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin
public class QuestoesController {

    @Autowired
    QuestoesService questoesService;
    @Autowired
    AdminsService adminsService;

    @Autowired
    InstituicoesService instituicoesService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findQuestionIp(@PathVariable UUID id) {
        Optional<Questoes> questao = questoesService.getQuestao(id);
        if (!questao.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                questao.get().add(linkTo(
                        methodOn(QuestoesController.class).findAll(questao.get().getInstituicao().getIdInstituicao()))
                        .withRel("Todos as Questões"));
                questao.get().add(linkTo(
                        methodOn(QuestoesController.class)
                                .findQuestionLimit(questao.get().getInstituicao().getIdInstituicao(), 10))
                        .withRel("Questões aleatorias com tamanho de 10"));
            } catch (Exception e) {
                System.out.println(e.getCause());
            }
            return new ResponseEntity<>(questao.get(), HttpStatus.OK);

        }

    }

    @GetMapping("/LimitRange/{idInstituicao}/{limit}")
    public ResponseEntity<Object> findQuestionLimit(@PathVariable UUID idInstituicao,
            @PathVariable int limit) {
        System.out.println(idInstituicao);
        Optional<Instituicoes> instituicao = instituicoesService.getInstituicao(idInstituicao);
        if (instituicao.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Questoes> questoesList = questoesService.findRandomRowsLimited(instituicao.get().getIdInstituicao(),
                    limit);
            if (questoesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                for (Questoes questao : questoesList) {
                    UUID id = questao.getIdQuestao();
                    questao.add(linkTo(methodOn(QuestoesController.class).findQuestionIp(id)).withSelfRel());
                }
                return new ResponseEntity<>(questoesList, HttpStatus.OK);
            }
        }

    }

    @GetMapping("/getAll/{idInstituicao}")
    public ResponseEntity<List<Questoes>> findAll(@PathVariable(value = "idInstituicao") UUID idInstituicao) {
        Optional<Instituicoes> instituicao = instituicoesService.getInstituicao(idInstituicao);
        if (instituicao.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Questoes> questoesList = questoesService.getAllQuestoesByInstituicao(instituicao.get());
            if (questoesList.isEmpty()) {
                return new ResponseEntity<List<Questoes>>(HttpStatus.NO_CONTENT);
            } else {
                for (Questoes questao : questoesList) {
                    UUID id = questao.getIdQuestao();
                    questao.add(linkTo(methodOn(QuestoesController.class).findQuestionIp(id)).withSelfRel());
                }
                return new ResponseEntity<List<Questoes>>(questoesList, HttpStatus.OK);
            }
        }
    }

    @PostMapping("/{adminNome}/{senhaAdmin}")
    public ResponseEntity<Questoes> cadastrarQuestion(@PathVariable(value = "adminNome") String adminNome,
            @PathVariable(value = "senhaAdmin") String senhaAdmin, @RequestBody @Valid QuestoesDTO questoesDto) {
        Optional<Admins> admin = adminsService.Login(adminNome, senhaAdmin);
        if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            Questoes questao = new Questoes();
            BeanUtils.copyProperties(questoesDto, questao);
            questao.setInstituicao(admin.get().getInstituicao());
            Questoes questaoPersistir = questoesService.Cadastrar(questao);
            questaoPersistir.add(
                    linkTo(methodOn(QuestoesController.class).findQuestionIp(questao.getIdQuestao())).withSelfRel());
            questaoPersistir.add(
                    linkTo(methodOn(QuestoesController.class).findAll(admin.get().getInstituicao().getIdInstituicao()))
                            .withRel("Todos as Questões"));
            questaoPersistir.add(linkTo(methodOn(QuestoesController.class).findQuestionLimit(
                    admin.get().getInstituicao().getIdInstituicao(),
                    10))
                    .withRel("Questões aleatorias com tamanho de 10"));
            return new ResponseEntity<Questoes>(questao, HttpStatus.OK);
        }

    }

    @PutMapping("/{adminNome}/{adminSenha}/{id}")
    public ResponseEntity<Object> updateQuestao(@PathVariable(value = "id") UUID id,
            @PathVariable(value = "adminNome") String adminNome, @PathVariable(value = "adminSenha") String adminSenha,
            @RequestBody @Valid QuestoesDTO questoesDTO) {
        Optional<Questoes> questao = questoesService.getQuestao(id);
        Optional<Admins> admin = adminsService.Login(adminNome, adminSenha);

        if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (questao.isEmpty()) {
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
        } else if (admin.get().getInstituicao() != questao.get().getInstituicao()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {

            BeanUtils.copyProperties(questoesDTO, questao);
            try {
                Questoes questaoAlterada = questoesService.Cadastrar(questao.get());
                questaoAlterada
                        .add(linkTo(methodOn(QuestoesController.class).findQuestionIp(id))
                                .withSelfRel());
                questaoAlterada.add(linkTo(methodOn(QuestoesController.class).findQuestionLimit(
                        admin.get().getInstituicao().getIdInstituicao(),
                        10))
                        .withRel("Questões aleatorias com tamanho de 10"));
                questaoAlterada.add(
                        linkTo(methodOn(QuestoesController.class)
                                .findAll(admin.get().getInstituicao().getIdInstituicao()))
                                .withRel("todas as questoes"));
                return new ResponseEntity<Object>(questaoAlterada, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

            }
        }
    }

    @DeleteMapping("/{adminNome}/{senhaAdmin}/{id}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable String adminNome,
            @PathVariable String senhaAdmin, @PathVariable UUID id) {
        Optional<Questoes> questao = questoesService.getQuestao(id);
        Optional<Admins> admin = adminsService.Login(adminNome, senhaAdmin);
        if (adminsService.Login(adminNome, senhaAdmin).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (questao.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (admin.get().getInstituicao() != questao.get().getInstituicao()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                questoesService.Deletar(questao.get().getIdQuestao());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
            }
        }
    }

}
