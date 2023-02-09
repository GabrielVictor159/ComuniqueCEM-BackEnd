package com.comunique.repository;

import com.comunique.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import com.comunique.model.Cronograma;
@Repository
public interface CronogramaRepository extends JpaRepository<Cronograma, UUID> {
    List<Cronograma> findByUsuario(Usuarios usuario);
}
