package com.comunique.comunique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import com.comunique.comunique.model.Chat;
@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {

}
