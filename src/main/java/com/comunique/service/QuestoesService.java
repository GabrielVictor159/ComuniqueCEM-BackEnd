package com.comunique.service;

import com.comunique.model.Questoes;
import com.comunique.repository.QuestoesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestoesService {
    @Autowired
    QuestoesRepository questoesRepository;

    @Transactional
    public Questoes Cadastrar(Questoes questoes){
        return questoesRepository.save(questoes);
    }

    public Optional<Questoes> getQuestao(UUID idQuestao){
        return questoesRepository.findById(idQuestao);
    }
    public List<Questoes> getAllQuestoes(){
        return questoesRepository.findAll();
    }

    @Transactional
    public void Deletar(Questoes questao){
        questoesRepository.delete(questao);
    }
}
