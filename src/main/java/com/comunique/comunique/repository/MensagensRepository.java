package com.comunique.comunique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comunique.comunique.model.Mensagens;
@Repository
public interface MensagensRepository extends JpaRepository<Mensagens, String> {

}
