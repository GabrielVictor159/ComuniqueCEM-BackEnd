package com.comunique.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.comunique.functions.MD5Encoder;
import com.comunique.model.enums.typeUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios implements Serializable {
	private static final long serialVersionUID = 1l;
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idUsuario;
	@Column(nullable = false)
	private String nomeUsuario;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private typeUsuario tipoUsuario;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	private String fotoPerfil;

	@Column(nullable = false)
	private boolean usuarioOnline;

	@ManyToOne
	@JoinColumn(name = "instituicao", nullable = false)
	private Instituicoes instituicao;

	@Column(nullable = true)
	private String senhaProvisoria;

	@Column(nullable = true)
	private Date dataSenhaProvisoria;

	public Usuarios() {
	}

	public Usuarios(UUID idUsuario, String nomeUsuario, typeUsuario tipoUsuario, String email, String senha,
			String fotoPerfil, boolean usuarioOnline, Instituicoes instituicao) {
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.tipoUsuario = tipoUsuario;
		this.email = email;
		this.senha = MD5Encoder.encode(senha);
		this.fotoPerfil = fotoPerfil;
		this.usuarioOnline = usuarioOnline;
		this.instituicao = instituicao;
	}

	public UUID getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(UUID idUsuario) {
		this.idUsuario = idUsuario;
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
		this.senha = MD5Encoder.encode(senha);
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

	public Instituicoes getInstituicao() {
		return this.instituicao;
	}

	public void setInstituicao(Instituicoes instituicao) {
		this.instituicao = instituicao;
	}

	public String getSenhaProvisoria() {
		return this.senhaProvisoria;
	}

	public void setSenhaProvisoria(String senhaProvisoria) {
		this.senhaProvisoria = MD5Encoder.encode(senhaProvisoria);
	}

	public Date getDataSenhaProvisoria() {
		return this.dataSenhaProvisoria;
	}

	public void setDataSenhaProvisoria(Date dataSenhaProvisoria) {
		this.dataSenhaProvisoria = dataSenhaProvisoria;
	}

	@Override
	public String toString() {
		return "{" +
				" idUsuario='" + getIdUsuario() + "'" +
				", nomeUsuario='" + getNomeUsuario() + "'" +
				", tipoUsuario='" + getTipoUsuario() + "'" +
				", email='" + getEmail() + "'" +
				", senha='" + getSenha() + "'" +
				", fotoPerfil='" + getFotoPerfil() + "'" +
				", usuarioOnline='" + isUsuarioOnline() + "'" +
				", instituicao='" + getInstituicao() + "'" +
				", senhaProvisoria='" + getSenhaProvisoria() + "'" +
				", dataSenhaProvisoria='" + getDataSenhaProvisoria() + "'" +
				"}";
	}

}
