package com.comunique.comunique.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuarios implements Serializable {
		private static final long serialVersionUID = 1l;
		@Id
		@Column
		private String nomeUsuario;
		
		@Column(nullable= false)
		private String tipoUsuario;
		@Column(nullable= false)
		private String email;
		@Column(nullable= false)
		private String senha;
		@Column(nullable= false)
		private String fotoPerfil;
		@Column(nullable= false)
		private String fotoBackground;
		@Column(nullable= false)
		private boolean usuarioOnline;
		
		
		public Usuarios(String nomeUsuario, String tipoUsuario, String email, String senha, String fotoPerfil,
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
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public String toString() {
			return "Usuarios [nomeUsuario=" + nomeUsuario + ", tipoUsuario=" + tipoUsuario + ", email=" + email
					+ ", senha=" + senha + ", fotoPerfil=" + fotoPerfil + ", fotoBackground=" + fotoBackground
					+ ", usuarioOnline=" + usuarioOnline + "]";
		}
		
}
