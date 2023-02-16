package com.comunique.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunique.model.Chat;
import com.comunique.model.Usuarios;
import com.comunique.repository.ChatRepository;

import jakarta.transaction.Transactional;

@Service
public class ChatService {
    @Autowired
    ChatRepository chatRepository;

    @Transactional
    public Chat Cadastrar(Chat chat) {
        return chatRepository.save(chat);
    }

    public Optional<Chat> getChatById(UUID idChat) {
        return chatRepository.findById(idChat);
    }

    public List<Chat> getChatByUser(Usuarios user) {
        return chatRepository.findAllByUser(user);
    }

    @Transactional
    public void Deletar(Chat chat) {
        chatRepository.delete(chat);
    }

    @Transactional
    public void DeletarAllByUsuario(Usuarios user) {
        chatRepository.deleteChatsByUsuario(user);
    }
}
