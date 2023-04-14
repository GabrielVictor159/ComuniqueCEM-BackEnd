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
		        // Utilize um email temporário para verificar se o email do usuário é válido
				String emailUsername = "SeuEmail";
				String emailPassword = "SuaSenha";
				String subject = "Verificação de e-mail";
				String body = "Este é um e-mail de verificação temporário. Por favor, ignore.";
		
				Email tempEmail = new Email(usuario.getEmail(), subject, body, emailUsername, emailPassword);
				String sendResult = emailService.sendEmail(tempEmail);
		
				// Se ocorrer algum erro relacionado ao endereço de e-mail, lançar uma exceção
				if (!sendResult.equals("E-mail enviado com sucesso")) {
					throw new RuntimeException("Falha na validação do e-mail: " + sendResult);
				}

		return usuariosRepository.save(usuario);
	}

	public String gerarSenhaTemporaria(int length) {
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder senhaTemporaria = new StringBuilder();
	
		for (int i = 0; i < length; i++) {
			int index = (int) (Math.random() * caracteres.length());
			senhaTemporaria.append(caracteres.charAt(index));
		}
	
		return senhaTemporaria.toString();
	}

	public void enviarSenhaTemporariaPorEmail(Usuarios usuario, String senhaTemporaria) {
		String emailUsername = "SeuEmail";
		String emailPassword = "SuaSenha";
		String subject = "Senha temporária";
		String body = "Sua senha temporária é: " + senhaTemporaria + ". Por favor, use-a para fazer login e altere sua senha imediatamente.";
	
		Email email = new Email(usuario.getEmail(), subject, body, emailUsername, emailPassword);
		emailService.sendEmail(email);
	}
	

	public Optional<Usuarios> getUser(UUID idUsuario) {
		return usuariosRepository.findById(idUsuario);
	}

	public Optional<Usuarios> Login(String email, String senha) {
    Optional<Usuarios> usuario = usuariosRepository.findByEmail(email);

    if (usuario.isPresent()) {
        // Verifica se a senha fornecida corresponde à senha armazenada ou à senha temporária
        if (MD5Encoder.encode(senha).equals(usuario.get().getSenha()) || senha.equals(usuario.get().getSenhaTemporaria())) {
            return usuario;
        }
    }

    return Optional.empty();
}

@Transactional
public void esqueciASenha(String email) {
    Optional<Usuarios> usuarioOptional = usuariosRepository.findByEmail(email);

    if (usuarioOptional.isPresent()) {
        Usuarios usuario = usuarioOptional.get();

        // Gere uma senha temporária e defina-a para o usuário
        String senhaTemporaria = gerarSenhaTemporaria(8);
        usuario.setSenhaTemporaria(senhaTemporaria);
        usuariosRepository.save(usuario);

        // Envie a senha temporária por e-mail
        enviarSenhaTemporariaPorEmail(usuario, senhaTemporaria);
    } else {
        throw new RuntimeException("Usuário não encontrado com o e-mail fornecido.");
    }
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
