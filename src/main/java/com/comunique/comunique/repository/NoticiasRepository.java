package com.comunique.comunique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.comunique.comunique.model.Noticias;
@Repository
public interface NoticiasRepository extends JpaRepository<Noticias, String> {

}
