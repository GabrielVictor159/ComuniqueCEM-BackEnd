package com.comunique.repository;

import com.comunique.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import com.comunique.model.Mensagens;
@Repository
public interface MensagensRepository extends JpaRepository<Mensagens, UUID> {
    List<Mensagens> findAllByChat(Chat chat);
    void deleteByidMensagensIn(List<UUID> idMensagens);
}
