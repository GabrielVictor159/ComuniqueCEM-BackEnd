package com.comunique.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comunique.config.SystemConfigs;
import com.comunique.functions.AleatoryString;
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
		try {
			return usuariosRepository.save(usuario);
		} catch (Exception e) {
			Usuarios user = new Usuarios();

			System.out.println("Erro ao cadastrar usuário: " + e.getMessage() + e.toString());
			return usuario;
		}
	}

	public Optional<Usuarios> getUser(UUID idUsuario) {
		return usuariosRepository.findById(idUsuario);
	}

	public Optional<Usuarios> getUserByEmail(String email) {
		return usuariosRepository.findByEmail(email);
	}

	public Optional<Usuarios> Login(String email, String Senha) {
		if ("default".equals(Senha)) {
			return Optional.empty();
		}
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
	public boolean enviarSenhaTemporariaPorEmail(Usuarios usuario) {
		try {
			String senhaTemporaria = AleatoryString.getAlphaNumericString(8);
			String emailUsername = SystemConfigs.Email;
			String emailPassword = SystemConfigs.senhaEmail;
			String subject = "Senha temporária";
			String body = "Sua senha temporária é: " + senhaTemporaria
					+ ". Por favor, use-a para fazer login, porem lembre-se essa senha sera apagada do sistema.";

			Email email = new Email(usuario.getEmail(), subject, body, emailUsername, emailPassword);
			emailService.sendEmail(email);
			usuario.setSenhaProvisoria(senhaTemporaria);
			usuario.setDataSenhaProvisoria(new Date());
			usuariosRepository.save(usuario);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	@Transactional
	public void updateForTimeSenhasTemporarias() {
		Date dataAtual = new Date();
		List<Usuarios> vetorUsuarios = usuariosRepository.findAllBySenhaTemporariaNotDefault();
		Calendar calAtual = Calendar.getInstance();
		Calendar calComparacao = Calendar.getInstance();
		calAtual.setTime(dataAtual);

		for (Usuarios usuario : vetorUsuarios) {
			calComparacao.setTime(usuario.getDataSenhaProvisoria());
			long diferencaEmMilissegundos = calAtual.getTimeInMillis() -
					calComparacao.getTimeInMillis();
			long diferencaEmDias = diferencaEmMilissegundos / (24 * 60 * 60 * 1000);
			if (diferencaEmDias > 1) {
				usuario.setSenhaProvisoria("default");
			}

		}
		usuariosRepository.saveAll(vetorUsuarios);
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
