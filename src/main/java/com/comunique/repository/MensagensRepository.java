package com.comunique.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comunique.model.Chat;
import com.comunique.model.Mensagens;

@Repository
public interface MensagensRepository extends JpaRepository<Mensagens, UUID> {
    List<Mensagens> findAllByChat(Chat chat);

    void deleteByidMensagensIn(List<UUID> idMensagens);

    void deleteAllByChat(Chat chat);

    Mensagens findTopByChatOrderByDataMensagemDesc(Chat chat);

    @Query("SELECT e FROM Mensagens e WHERE e.lida = true AND e.entregue = true")
    List<Mensagens> findAllByLidaAndEntregue();

    @Modifying
    @Query("UPDATE Mensagens e SET e.lida = true, e.entregue = true WHERE e.chat = :chat AND e.usuarioEnviou != :usuarioId")
    int usuarioLeuChat(@Param("chat") Chat chat, @Param("usuarioId") UUID usuarioId);

}
