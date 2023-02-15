package com.comunique.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comunique.model.Chat;
import com.comunique.model.Usuarios;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {
    @Query("SELECT c FROM Chat c WHERE c.usuario1 = :usuario OR c.usuario2 = :usuario")
    List<Chat> findAllByUser(@Param("usuario") Usuarios user);

}
