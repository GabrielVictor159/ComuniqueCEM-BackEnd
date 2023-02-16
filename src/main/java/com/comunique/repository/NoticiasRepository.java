package com.comunique.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comunique.model.Instituicoes;
import com.comunique.model.Noticias;

@Repository
public interface NoticiasRepository extends JpaRepository<Noticias, UUID> {
    Page<Noticias> findAllByInstituicaoOrderByIdNoticiaDesc(Instituicoes instituicao, Pageable pageable);

    List<Noticias> findAllByInstituicao(Instituicoes instituicao);
}
