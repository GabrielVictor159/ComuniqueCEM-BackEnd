package com.comunique.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunique.functions.MD5Encoder;
import com.comunique.model.AdminsMaster;
import com.comunique.repository.AdminsMasterRepository;

import jakarta.transaction.Transactional;

@Service
public class AdminsMasterService {
    @Autowired
    AdminsMasterRepository adminsMasterRepository;

    @Transactional
    public AdminsMaster Cadastrar(AdminsMaster adminMaster) {
        return adminsMasterRepository.save(adminMaster);
    }

    public Optional<AdminsMaster> Login(String Nome, String Senha) {
        Optional<AdminsMaster> admin = adminsMasterRepository.login(Nome, MD5Encoder.encode(Senha));
        return admin;
    }

    @Transactional
    public void Deletar(UUID id) {
        adminsMasterRepository.deleteById(id);
    }
}
