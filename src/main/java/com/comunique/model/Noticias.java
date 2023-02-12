package com.comunique.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	public Noticias() {
		super();
	}

	public Noticias(UUID idNoticia, String titulo, String imagem, String texto) {
		super();
		this.idNoticia = idNoticia;
		this.titulo = titulo;
		this.imagem = imagem;
		this.texto = texto;
	}

	public UUID getIdNoticia() {
		return idNoticia;
	}

	public void setIdNoticia(UUID idNoticia) {
		this.idNoticia = idNoticia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Noticias [idNoticia=" + idNoticia + ", titulo=" + titulo + ", imagem=" + imagem + ", texto=" + texto
				+ "]";
	}

}
