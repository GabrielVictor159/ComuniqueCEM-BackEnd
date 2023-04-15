package com.comunique.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "noticias")
public class Noticias extends RepresentationModel<Noticias> implements Serializable {
	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idNoticia;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String imagem;

	@Column(nullable = false)
	private String texto;

	@ManyToOne
	@JoinColumn(name = "instituicao", nullable = false)
	private Instituicoes instituicao;

	public Noticias() {
	}

	public Noticias(UUID idNoticia, String titulo, String imagem, String texto, Instituicoes instituicao) {
		this.idNoticia = idNoticia;
		this.titulo = titulo;
		this.imagem = imagem;
		this.texto = texto;
		this.instituicao = instituicao;
	}

	public UUID getIdNoticia() {
		return this.idNoticia;
	}

	public void setIdNoticia(UUID idNoticia) {
		this.idNoticia = idNoticia;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImagem() {
		return this.imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
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
				" idNoticia='" + getIdNoticia() + "'" +
				", titulo='" + getTitulo() + "'" +
				", imagem='" + getImagem() + "'" +
				", texto='" + getTexto() + "'" +
				", instituicao='" + getInstituicao() + "'" +
				"}";
	}

}
