package com.comunique.comunique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.comunique.comunique.model.Questoes;
@Repository
public interface QuestoesRepository extends JpaRepository<Questoes, String> {

}
