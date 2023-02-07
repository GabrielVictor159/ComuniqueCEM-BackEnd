package com.comunique.service;

import com.comunique.model.Noticias;
import com.comunique.repository.NoticiasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticiasService {
    @Autowired
    NoticiasRepository noticiasRepository;

    @Transactional
    public Noticias Cadastrar(Noticias noticia){
            return noticiasRepository.save(noticia);
    }
    public Optional<Noticias> getNoticia(Noticias noticia){
        return noticiasRepository.findById(noticia.getIdNoticia());
    }

    public List<Noticias> getAllNoticias(){
        return noticiasRepository.findAll();
    }

    @Transactional
    public void Deletar(Noticias noticia){
        noticiasRepository.delete(noticia);
    }
}
