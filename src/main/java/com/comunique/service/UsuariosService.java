package com.comunique.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunique.functions.MD5Encoder;
import com.comunique.model.Instituicoes;
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

	public Optional<Usuarios> Login(String email, String Senha) {
		Optional<Usuarios> teste = usuariosRepository.login(email, MD5Encoder.encode(Senha));
		return teste;
	}

	public List<Usuarios> getAllUsuariosInstituicao(Instituicoes instituicao) {
		return usuariosRepository.findAllByInstituicao(instituicao);
	}

	@Transactional
	public void Deletar(UUID id) {
		usuariosRepository.deleteById(id);

	}

	@Transactional
	public void DeletarAllByInstituicao(Instituicoes instituicao) {
		usuariosRepository.deleteAllByInstituicao(instituicao);
	}

}
