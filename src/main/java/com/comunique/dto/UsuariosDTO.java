package com.comunique.dto;

import javax.validation.constraints.NotBlank;

import com.comunique.model.Instituicoes;

public class UsuariosDTO {
	@NotBlank
	private String nomeUsuario;
	@NotBlank
	private String tipoUsuario;
	@NotBlank
	private String email;
	@NotBlank
	private String senha;
	@NotBlank
	private String fotoPerfil;
	@NotBlank
	private String fotoBackground;
	@NotBlank
	private boolean usuarioOnline;
	@NotBlank
	private Instituicoes instituicao;

	public UsuariosDTO() {
	}

	public UsuariosDTO(String nomeUsuario, String tipoUsuario, String email, String senha, String fotoPerfil,
			String fotoBackground, boolean usuarioOnline, Instituicoes instituicao) {
		this.nomeUsuario = nomeUsuario;
		this.tipoUsuario = tipoUsuario;
		this.email = email;
		this.senha = senha;
		this.fotoPerfil = fotoPerfil;
		this.fotoBackground = fotoBackground;
		this.usuarioOnline = usuarioOnline;
		this.instituicao = instituicao;
	}

	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFotoPerfil() {
		return this.fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getFotoBackground() {
		return this.fotoBackground;
	}

	public void setFotoBackground(String fotoBackground) {
		this.fotoBackground = fotoBackground;
	}

	public boolean isUsuarioOnline() {
		return this.usuarioOnline;
	}

	public boolean getUsuarioOnline() {
		return this.usuarioOnline;
	}

	public void setUsuarioOnline(boolean usuarioOnline) {
		this.usuarioOnline = usuarioOnline;
	}

	public Instituicoes getInstituicao() {
		return this.instituicao;
	}

	public void setInstituicao(Instituicoes instituicao) {
		this.instituicao = instituicao;
	}

	@Override
	public String toString() {
		return "{" +
				" nomeUsuario='" + getNomeUsuario() + "'" +
				", tipoUsuario='" + getTipoUsuario() + "'" +
				", email='" + getEmail() + "'" +
				", senha='" + getSenha() + "'" +
				", fotoPerfil='" + getFotoPerfil() + "'" +
				", fotoBackground='" + getFotoBackground() + "'" +
				", usuarioOnline='" + isUsuarioOnline() + "'" +
				", instituicao='" + getInstituicao() + "'" +
				"}";
	}

}
