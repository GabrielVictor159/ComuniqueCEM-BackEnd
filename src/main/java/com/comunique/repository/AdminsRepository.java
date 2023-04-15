package com.comunique.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.comunique.model.Admins;
import com.comunique.model.Instituicoes;

@Repository
public interface AdminsRepository extends JpaRepository<Admins, UUID> {
    @Query("SELECT e FROM Admins e WHERE e.nome = ?1 AND e.senha = ?2")
    Optional<Admins> login(String Nome, String Senha);

    List<Admins> findAllByInstituicao(Instituicoes instituicao);

    void deleteAllByInstituicao(Instituicoes instituicao);
}
