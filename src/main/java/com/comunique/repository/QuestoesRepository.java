package com.comunique.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.comunique.model.Instituicoes;
import com.comunique.model.Questoes;

@Repository
public interface QuestoesRepository extends JpaRepository<Questoes, UUID> {
    @Query(value = "SELECT * FROM questoes WHERE instituicao = ?1 ORDER BY RAND() LIMIT ?2", nativeQuery = true)
    List<Questoes> findRandomRowsLimited(UUID instituicao, int limit);

    List<Questoes> findAllByInstituicao(Instituicoes instituicao);

    void deleteAllByInstituicao(Instituicoes instituicao);
}
