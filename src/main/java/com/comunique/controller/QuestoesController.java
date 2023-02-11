package com.comunique.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comunique.model.Questoes;
import com.comunique.service.QuestoesService;

@RestController
@RequestMapping(value = "/Questoes", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin
public class QuestoesController {

    @Autowired
    QuestoesService questoesService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findQuestionIp(@PathVariable(value = "id") UUID id) {
        Optional<Questoes> questao = questoesService.getQuestao(id);
        if (!questao.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Questão não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(questao.get());
    }

    @GetMapping("/LimitRange/{limit}")
    public ResponseEntity<Object> findQuestionLimit(@PathVariable(value = "limit") int limit) {
        return ResponseEntity.status(HttpStatus.OK).body(questoesService.findRandomRowsLimited(limit));
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(questoesService.getAllQuestoes());
    }

}
