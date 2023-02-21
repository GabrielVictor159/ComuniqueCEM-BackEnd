package com.comunique.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.comunique.model.Instituicoes;
import com.comunique.model.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, UUID> {

    @Query("SELECT e FROM Usuarios e WHERE e.email = ?1 AND e.senha = ?2")
    Optional<Usuarios> login(String Email, String Senha);

    List<Usuarios> findAllByInstituicao(Instituicoes instituicao);

    void deleteAllByInstituicao(Instituicoes instituicao);
}
