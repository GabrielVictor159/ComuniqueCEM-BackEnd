package com.comunique.model;

import java.io.Serializable;
import java.util.UUID;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.boot.autoconfigure.domain.EntityScan;



@Entity
@Table(name="usuarios")
public class Usuarios implements Serializable {
		private static final long serialVersionUID = 1l;
		  @Id
		  @GeneratedValue(strategy = GenerationType.UUID)
		  private UUID idUsuario;
		  @Column(nullable=false)
		  private String nomeUsuario;
		  
		  @Column(nullable=false)
		  private String tipoUsuario;
		  
		  @Column(nullable=false, unique=true)
		  private String email;
		  
		  @Column(nullable=false)
		  private String senha;
		  
		  @Column(nullable=false)
		  private String fotoPerfil;
		  
		  @Column(nullable=false)
		  private String fotoBackground;
		  
		  @Column(nullable=false)
		  private boolean usuarioOnline;

		public Usuarios(UUID idUsuario, String nomeUsuario, String tipoUsuario, String email, String senha,
				String fotoPerfil, String fotoBackground, boolean usuarioOnline) {
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

		public Usuarios() {

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

		public static long getSerialversionuid() {
			return serialVersionUID;
		}


	@Override
	public String toString() {
		return "Usuarios{" +
				"idUsuario=" + idUsuario +
				", nomeUsuario='" + nomeUsuario + '\'' +
				", tipoUsuario='" + tipoUsuario + '\'' +
				", email='" + email + '\'' +
				", senha='" + senha + '\'' +
				", fotoPerfil='" + fotoPerfil + '\'' +
				", fotoBackground='" + fotoBackground + '\'' +
				", usuarioOnline=" + usuarioOnline +
				'}';
	}
}
