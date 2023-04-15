package com.comunique.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.comunique.model.enums.typeUsuario;

public class UsuariosDTO {
	@NotBlank
	@Size(min = 5)
	private String nomeUsuario;
	@NotBlank
	private typeUsuario tipoUsuario;
	@NotBlank
	private String email;
	@NotBlank
	@Size(min = 8)
	private String senha;
	@NotBlank
	private String fotoPerfil;
	@NotBlank
	private boolean usuarioOnline;

	public UsuariosDTO() {
	}

	public UsuariosDTO(String nomeUsuario, typeUsuario tipoUsuario, String email, String senha, String fotoPerfil,
			boolean usuarioOnline) {
		this.nomeUsuario = nomeUsuario;
		this.tipoUsuario = tipoUsuario;
		this.email = email;
		this.senha = senha;
		this.fotoPerfil = fotoPerfil;
		this.usuarioOnline = usuarioOnline;
	}

	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public typeUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(typeUsuario tipoUsuario) {
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

	public boolean isUsuarioOnline() {
		return this.usuarioOnline;
	}

	public boolean getUsuarioOnline() {
		return this.usuarioOnline;
	}

	public void setUsuarioOnline(boolean usuarioOnline) {
		this.usuarioOnline = usuarioOnline;
	}

	@Override
	public String toString() {
		return "{" +
				" nomeUsuario='" + getNomeUsuario() + "'" +
				", tipoUsuario='" + getTipoUsuario() + "'" +
				", email='" + getEmail() + "'" +
				", senha='" + getSenha() + "'" +
				", fotoPerfil='" + getFotoPerfil() + "'" +
				", usuarioOnline='" + isUsuarioOnline() + "'" +
				"}";
	}

}
