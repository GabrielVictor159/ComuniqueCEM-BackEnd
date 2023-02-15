package com.comunique.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.comunique.model.Instituicoes;

@Repository
public interface InstituicoesRepository extends JpaRepository<Instituicoes, UUID> {
    @Query("SELECT e FROM Instituicoes e WHERE e.nome = ?1 AND e.senha = ?2")
    Optional<Instituicoes> loginUsuario(String nome, String Senha);

    @Query("SELECT e FROM Instituicoes e WHERE e.nome = ?1 AND e.senhaProfessores = ?2")
    Optional<Instituicoes> loginProfessores(String nome, String Senha);

}
