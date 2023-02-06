package com.comunique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import com.comunique.model.Questoes;
@Repository
public interface QuestoesRepository extends JpaRepository<Questoes, UUID> {

}
