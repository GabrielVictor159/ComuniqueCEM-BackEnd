package com.comunique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import com.comunique.model.Noticias;
@Repository
public interface NoticiasRepository extends JpaRepository<Noticias, UUID> {

}
