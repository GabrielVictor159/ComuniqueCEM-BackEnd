package com.comunique.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunique.functions.MD5Encoder;
import com.comunique.model.Admins;
import com.comunique.model.Instituicoes;
import com.comunique.repository.AdminsRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminsService {
    @Autowired
    AdminsRepository adminsRepository;

    @Transactional
    public Admins Cadastrar(Admins admin) {
        return adminsRepository.save(admin);
    }

    public Optional<Admins> getOne(UUID id) {
        return adminsRepository.findById(id);
    }

    public Optional<Admins> Login(String Nome, String Senha) {
        Optional<Admins> admin = adminsRepository.login(Nome, MD5Encoder.encode(Senha));
        return admin;
    }

    public List<Admins> getAll() {
        return adminsRepository.findAll();
    }

    public List<Admins> getAllByInstituicao(Instituicoes instituicao) {
        return adminsRepository.findAllByInstituicao(instituicao);
    }

    @Transactional
    public void Deletar(UUID id) {
        adminsRepository.deleteById(id);
    }
}
