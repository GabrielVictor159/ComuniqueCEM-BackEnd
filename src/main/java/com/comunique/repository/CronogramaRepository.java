package com.comunique.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comunique.model.Cronograma;
import com.comunique.model.Usuarios;

@Repository
public interface CronogramaRepository extends JpaRepository<Cronograma, UUID> {
    List<Cronograma> findByUsuario(Usuarios usuario);

    void deleteAllByUsuario(Usuarios usuario);
}
