package com.comunique.service;

import com.comunique.model.Chat;
import com.comunique.model.Mensagens;
import com.comunique.repository.MensagensRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MensagensService {
    @Autowired
    MensagensRepository mensagensRepository;

    @Transactional
    public Mensagens Cadastrar(Mensagens mensagem){
        return  mensagensRepository.save(mensagem);
    }
    public Optional<Mensagens> getMensagem(UUID idMensagem){
        return  mensagensRepository.findById(idMensagem);
    }
    public List<Mensagens> getAllMensagensForChat(Chat chat){
        return  mensagensRepository.findAllByChat(chat);
    }
    @Transactional
    public void Deletar(UUID idMensagem){
        mensagensRepository.deleteById(idMensagem);
    }

    @Transactional
    public void DeleteIn(List<UUID> idMensagens){
        mensagensRepository.deleteByidMensagensIn(idMensagens);
    }

    @Transactional
    public void DeleteForChat(Chat chat){mensagensRepository.deleteAllByChat(chat);}
}
