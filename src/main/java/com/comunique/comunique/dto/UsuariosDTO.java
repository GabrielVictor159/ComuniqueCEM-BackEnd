package com.comunique.comunique.dto;

public class UsuariosDTO {
	private String nomeUsuario;
	private String tipoUsuario;
	private String email;
	private String senha;
	private String fotoPerfil;
	private String fotoBackground;
	private boolean usuarioOnline;

	public UsuariosDTO(String nomeUsuario, String tipoUsuario, String email, String senha, String fotoPerfil,
			String fotoBackground, boolean usuarioOnline) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.tipoUsuario = tipoUsuario;
		this.email = email;
		this.senha = senha;
		this.fotoPerfil = fotoPerfil;
		this.fotoBackground = fotoBackground;
		this.usuarioOnline = usuarioOnline;
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
		return "UsuariosDTO [nomeUsuario=" + nomeUsuario + ", tipoUsuario=" + tipoUsuario + ", email=" + email
				+ ", senha=" + senha + ", fotoPerfil=" + fotoPerfil + ", fotoBackground=" + fotoBackground
				+ ", usuarioOnline=" + usuarioOnline + "]";
	}
	
}
