package com.comunique.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comunique.model.Instituicoes;
import com.comunique.model.Noticias;
import com.comunique.repository.NoticiasRepository;

import jakarta.transaction.Transactional;

@Service
public class NoticiasService {
    @Autowired
    NoticiasRepository noticiasRepository;

    @Transactional
    public Noticias Cadastrar(Noticias noticia) {
        return noticiasRepository.save(noticia);
    }

    public Optional<Noticias> getNoticia(UUID noticia) {
        return noticiasRepository.findById(noticia);
    }

    public List<Noticias> getAllNoticias() {
        return noticiasRepository.findAll();
    }

    public List<Noticias> getAllNoticiasInsituto(Instituicoes instituicao) {
        return noticiasRepository.findAllByInstituicao(instituicao);
    }

    public List<Noticias> getAllNoticiasPageable(Instituicoes instituicao, Pageable pageable) {
        return noticiasRepository.findAllByInstituicaoOrderByIdNoticiaDesc(instituicao, pageable).getContent();
    }

    @Transactional
    public void Deletar(UUID noticia) {
        noticiasRepository.deleteById(noticia);
    }
}
