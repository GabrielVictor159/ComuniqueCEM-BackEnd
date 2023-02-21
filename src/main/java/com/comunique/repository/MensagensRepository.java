package com.comunique.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comunique.model.Chat;
import com.comunique.model.Mensagens;

@Repository
public interface MensagensRepository extends JpaRepository<Mensagens, UUID> {
    List<Mensagens> findAllByChat(Chat chat);

    void deleteByidMensagensIn(List<UUID> idMensagens);

    void deleteAllByChat(Chat chat);

    Mensagens findTopByChatOrderByDataMensagemDesc(Chat chat);
}
