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
import com.comunique.model.Questoes;
import com.comunique.service.AdminsService;
import com.comunique.service.QuestoesService;

@RestController
@RequestMapping(value = "/Questoes", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin
public class QuestoesController {

    @Autowired
    QuestoesService questoesService;
    @Autowired
    AdminsService adminsService;

    @GetMapping("/{id}")
    public ResponseEntity<Questoes> findQuestionIp(@PathVariable(value = "id") UUID id) {
        Optional<Questoes> questao = questoesService.getQuestao(id);
        if (!questao.isPresent()) {
            return new ResponseEntity<Questoes>(HttpStatus.NOT_FOUND);
        } else {
            questao.get().add(linkTo(methodOn(QuestoesController.class).findAll()).withRel("Todos as Questões"));
            questao.get().add(linkTo(methodOn(QuestoesController.class).findQuestionLimit(10))
                    .withRel("Questões aleatorias com tamanho de 10"));

        }

        return new ResponseEntity<Questoes>(questao.get(), HttpStatus.OK);
    }

    @GetMapping("/LimitRange/{limit}")
    public ResponseEntity<List<Questoes>> findQuestionLimit(@PathVariable(value = "limit") int limit) {
        List<Questoes> questoesList = questoesService.findRandomRowsLimited(limit);
        if (questoesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (Questoes questao : questoesList) {
                UUID id = questao.getIdQuestao();
                questao.add(linkTo(methodOn(QuestoesController.class).findQuestionIp(id)).withSelfRel());
            }
            return new ResponseEntity<List<Questoes>>(questoesList, HttpStatus.OK);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Questoes>> findAll() {
        List<Questoes> questoesList = questoesService.getAllQuestoes();
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

    @PostMapping("/{adminNome}/{senhaAdmin}")
    public ResponseEntity<Questoes> cadastrarQuestion(@PathVariable(value = "adminNome") String adminNome,
            @PathVariable(value = "senhaAdmin") String senhaAdmin, @RequestBody @Valid QuestoesDTO questoesDto) {
        if (adminsService.Login(adminNome, senhaAdmin).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            Questoes questao = new Questoes();
            BeanUtils.copyProperties(questoesDto, questao);
            Questoes questaoPersistir = questoesService.Cadastrar(questao);
            questaoPersistir.add(
                    linkTo(methodOn(QuestoesController.class).findQuestionIp(questao.getIdQuestao())).withSelfRel());
            questaoPersistir.add(linkTo(methodOn(QuestoesController.class).findAll()).withRel("Todos as Questões"));
            questaoPersistir.add(linkTo(methodOn(QuestoesController.class).findQuestionLimit(10))
                    .withRel("Questões aleatorias com tamanho de 10"));
            return new ResponseEntity<Questoes>(questao, HttpStatus.OK);
        }

    }

    @PutMapping("/{adminNome}/{adminSenha}/{id}")
    public ResponseEntity<Object> updateQuestao(@PathVariable(value = "id") UUID id,
            @PathVariable(value = "adminNome") String adminNome, @PathVariable(value = "adminSenha") String adminSenha,
            @RequestBody @Valid QuestoesDTO questoesDTO) {
        Optional<Questoes> questao = questoesService.getQuestao(id);
        if (adminsService.Login(adminNome, adminSenha).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            if (questao.isEmpty()) {
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            } else {
                BeanUtils.copyProperties(questoesDTO, questao);
                try {
                    Questoes questaoAlterada = questoesService.Cadastrar(questao.get());
                    questaoAlterada
                            .add(linkTo(methodOn(QuestoesController.class).findQuestionIp(id))
                                    .withSelfRel());
                    questaoAlterada.add(linkTo(methodOn(QuestoesController.class).findQuestionLimit(10))
                            .withRel("Questões aleatorias com tamanho de 10"));
                    questaoAlterada.add(
                            linkTo(methodOn(QuestoesController.class).findAll()).withRel("todas as questoes"));
                    return new ResponseEntity<Object>(questaoAlterada, HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
    }

    @DeleteMapping("/{adminNome}/{senhaAdmin}/{questao}")
    public ResponseEntity<Object> deleteQuestion(@PathVariable(value = "adminNome") String adminNome,
            @PathVariable(value = "senhaNome") String senhaAdmin, @PathVariable(value = "questao") UUID questao) {
        if (adminsService.Login(adminNome, senhaAdmin).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                questoesService.Deletar(questao);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
            }
        }
    }

}
