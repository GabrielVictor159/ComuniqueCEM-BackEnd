package com.comunique.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunique.functions.MD5Encoder;
import com.comunique.model.Admins;
import com.comunique.repository.AdminsRepository;

@Service
public class AdminsService {
    @Autowired
    AdminsRepository adminsRepository;

    public boolean Login(String Nome, String Senha) {
        Optional<Admins> teste = adminsRepository.login(Nome, MD5Encoder.encode(Senha));
        return teste.isPresent();
    }
}
