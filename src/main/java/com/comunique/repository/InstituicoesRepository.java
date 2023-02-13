package com.comunique.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comunique.model.Instituicoes;

@Repository
public interface InstituicoesRepository extends JpaRepository<Instituicoes, UUID> {

}
