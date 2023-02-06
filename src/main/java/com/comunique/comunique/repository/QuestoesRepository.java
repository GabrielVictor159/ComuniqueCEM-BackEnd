package com.comunique.comunique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import com.comunique.comunique.model.Questoes;
@Repository
public interface QuestoesRepository extends JpaRepository<Questoes, UUID> {

}
