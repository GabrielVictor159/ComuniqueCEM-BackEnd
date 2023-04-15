package com.comunique.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.comunique.model.AdminsMaster;

@Repository
public interface AdminsMasterRepository extends JpaRepository<AdminsMaster, UUID> {
    @Query("SELECT e FROM AdminsMaster e WHERE e.nome = ?1 AND e.senha = ?2")
    Optional<AdminsMaster> login(String Nome, String Senha);
}
