package com.comunique.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunique.functions.MD5Encoder;
import com.comunique.model.Instituicoes;
import com.comunique.repository.InstituicoesRepository;

import jakarta.transaction.Transactional;

@Service
public class InstituicoesService {

    @Autowired
    InstituicoesRepository instituicoesRepository;

    @Transactional
    public Instituicoes Cadastrar(Instituicoes instituicao) {
        return instituicoesRepository.save(instituicao);
    }

    public Optional<Instituicoes> getInstituicao(UUID id) {
        return instituicoesRepository.findById(id);
    }

    public List<Instituicoes> getAll() {
        return instituicoesRepository.findAll();
    }

    public Optional<Instituicoes> LoginUsuario(String nomeInstituicao, String senha) {
        return instituicoesRepository.loginUsuario(nomeInstituicao, MD5Encoder.encode(senha));
    }

    public Optional<Instituicoes> LoginProfessores(String nomeInstituicao, String senha) {
        return instituicoesRepository.loginProfessores(nomeInstituicao, MD5Encoder.encode(senha));
    }

    @Transactional
    public void Deletar(UUID id) {
        instituicoesRepository.deleteById(id);
    }

}
