package com.comunique.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comunique.model.Instituicoes;
import com.comunique.model.UsuariosSolicitacoes;

@Repository
public interface UsuariosSolicitacoesRepository extends JpaRepository<UsuariosSolicitacoes, UUID> {
    void deleteAllByInstituicao(Instituicoes instituicao);
}
