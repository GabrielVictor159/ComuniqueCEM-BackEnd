package com.comunique.comunique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.comunique.comunique.model.Cronograma;
@Repository
public interface CronogramaRepository extends JpaRepository<Cronograma, String> {

}
