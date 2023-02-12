package com.comunique.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comunique.model.Noticias;

@Repository
public interface NoticiasRepository extends JpaRepository<Noticias, UUID> {
    Page<Noticias> findAllByOrderByIdNoticiaDesc(Pageable pageable);
}
