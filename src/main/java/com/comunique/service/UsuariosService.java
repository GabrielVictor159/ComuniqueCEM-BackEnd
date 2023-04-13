package com.comunique.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comunique.functions.MD5Encoder;
import com.comunique.model.Email;
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
	@Autowired
	EmailService emailService;
	private static String GlobalPath = "src/main/resources/static/images/";

	@Transactional
	public Usuarios Cadastrar(Usuarios usuario) {
		String token = generateToken();
		usuario.setEmailVerificationToken(token);
	
		Usuarios savedUser = usuariosRepository.save(usuario);
	
		String verificationLink = "mysql://localhost:3306/comunique/verify-email?token=" + token;
		
		String emailBody = "Clique no link a seguir para verificar seu e-mail: <a href=\"" + verificationLink + "\">" + verificationLink + "</a>";

		//Não sei o que deveria ser o username
		String emailUsername = "ComuniqueCEM";
		//Não sei o que deveria ser a senha
		String emailPassword = "comuniquecem123";

		Email verificationEmail = new Email(usuario.getEmail(), "Verificação de e-mail", emailBody, emailUsername, emailPassword);
	
		emailService.sendEmail(verificationEmail);
	
		return savedUser;
	}

	public String generateToken() {
		return UUID.randomUUID().toString();
	}
	
	public Optional<Usuarios> findByEmailVerificationToken(String emailVerificationToken) {
		return usuariosRepository.findByEmailVerificationToken(emailVerificationToken);
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
				imageService.excluir(GlobalPath + usuario.get().getFotoPerfil());
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
				imageService.excluir(GlobalPath + usuario.getFotoPerfil());
			} catch (Exception e) {

			}
		}
		usuariosRepository.deleteAllByInstituicao(instituicao);
	}

}
