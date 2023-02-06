package com.comunique.comunique.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class UsuariosDTO {
	@NotBlank
	private UUID idUsuario;
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
	public UsuariosDTO(@NotBlank UUID idUsuario, @NotBlank String nomeUsuario, @NotBlank String tipoUsuario,
			@NotBlank String email, @NotBlank String senha, @NotBlank String fotoPerfil,
			@NotBlank String fotoBackground, @NotBlank boolean usuarioOnline) {
		super();
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.tipoUsuario = tipoUsuario;
		this.email = email;
		this.senha = senha;
		this.fotoPerfil = fotoPerfil;
		this.fotoBackground = fotoBackground;
		this.usuarioOnline = usuarioOnline;
	}
	public UUID getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(UUID idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	public String getFotoBackground() {
		return fotoBackground;
	}
	public void setFotoBackground(String fotoBackground) {
		this.fotoBackground = fotoBackground;
	}
	public boolean isUsuarioOnline() {
		return usuarioOnline;
	}
	public void setUsuarioOnline(boolean usuarioOnline) {
		this.usuarioOnline = usuarioOnline;
	}
	@Override
	public String toString() {
		return "UsuariosDTO [idUsuario=" + idUsuario + ", nomeUsuario=" + nomeUsuario + ", tipoUsuario=" + tipoUsuario
				+ ", email=" + email + ", senha=" + senha + ", fotoPerfil=" + fotoPerfil + ", fotoBackground="
				+ fotoBackground + ", usuarioOnline=" + usuarioOnline + "]";
	}
	
	
	


	
}
