package com.comunique.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunique.functions.MD5Encoder;
import com.comunique.model.Usuarios;
import com.comunique.repository.UsuariosRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuariosService {
	@Autowired
	UsuariosRepository usuariosRepository;

	@Transactional
	public Usuarios Cadastrar(Usuarios usuario) {
		return usuariosRepository.save(usuario);
	}

	public Optional<Usuarios> getUser(UUID idUsuario) {
		return usuariosRepository.findById(idUsuario);
	}

	public List<Usuarios> getAllUsers() {
		return usuariosRepository.findAll();
	}

	public boolean Login(String email, String Senha) {
		Optional<Usuarios> teste = usuariosRepository.login(email, MD5Encoder.encode(Senha));
		return teste.isPresent();
	}

	@Transactional
	public void Deletar(Usuarios usuarios) {
		usuariosRepository.delete(usuarios);
	}

}
