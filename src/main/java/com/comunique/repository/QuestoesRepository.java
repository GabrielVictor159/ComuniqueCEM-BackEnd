package com.comunique.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.comunique.model.Questoes;

@Repository
public interface QuestoesRepository extends JpaRepository<Questoes, UUID> {
    @Query(value = "SELECT * FROM Questoes ORDER BY RAND() LIMIT ?1", nativeQuery = true)
    List<Questoes> findRandomRowsLimited(int limit);
}
