package com.comunique.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.comunique.model.Instituicoes;
import com.comunique.model.UsuariosSolicitacoes;
import com.comunique.repository.UsuariosSolicitacoesRepository;

@Service
public class UsuariosSolicitacoesService {
    @Autowired
    UsuariosSolicitacoesRepository usuariosSolicitacoesRepository;

    @Transactional
    public UsuariosSolicitacoes cadastrar(UsuariosSolicitacoes usuariosSolicitacoes) {
        return usuariosSolicitacoesRepository.save(usuariosSolicitacoes);
    }

    public Optional<UsuariosSolicitacoes> buscarId(UUID id) {
        return usuariosSolicitacoesRepository.findById(id);
    }

    @Transactional
    public void Deletar(UUID id) {
        usuariosSolicitacoesRepository.deleteById(id);
    }

    @Transactional
    public void DeletarAllByInstituicao(Instituicoes instituicao) {
        usuariosSolicitacoesRepository.deleteAllByInstituicao(instituicao);
    }

}
