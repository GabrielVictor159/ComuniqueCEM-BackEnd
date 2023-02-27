package com.comunique.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
	@Autowired
	ImageService imageService;
	private static String GlobalPath = "src/main/resources/static/images/";

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

	public List<Usuarios> getAllUsuariosInstituicaoPaginado(Instituicoes instituicao, Pageable pageable) {
		return usuariosRepository.findAllByInstituicao(instituicao, pageable).getContent();
	}

	@Transactional
	public void Deletar(UUID id) {
		Optional<Usuarios> usuario = this.getUser(id);
		if (usuario.isPresent()) {
			try {
				imageService.excluir("", GlobalPath + usuario.get().getFotoPerfil());
				imageService.excluir("", GlobalPath + usuario.get().getFotoBackground());
			} catch (Exception e) {

			}
		}

		usuariosRepository.deleteById(id);

	}

	@Transactional
	public void DeletarAllByInstituicao(Instituicoes instituicao) {
		List<Usuarios> list = this.getAllUsuariosInstituicao(instituicao);
		for (Usuarios usuario : list) {
			try {
				imageService.excluir("", GlobalPath + usuario.getFotoPerfil());
				imageService.excluir("", GlobalPath + usuario.getFotoBackground());
			} catch (Exception e) {

			}
		}
		usuariosRepository.deleteAllByInstituicao(instituicao);
	}

}
