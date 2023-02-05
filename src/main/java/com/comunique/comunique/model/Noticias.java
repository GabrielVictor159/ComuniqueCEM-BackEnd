package com.comunique.comunique.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="noticias")
public class Noticias implements Serializable {
	private static final long serialVersionUID = 1l;
	
	@Id
	@Column
	private String idNoticia;
	
	@Column(nullable=false)
	private String titulo;
	
	@Column(nullable=false)
	private String imagem;
	
	@Column(nullable=false)
	private String texto;

	
	public Noticias(String idNoticia, String titulo, String imagem, String texto) {
		super();
		this.idNoticia = idNoticia;
		this.titulo = titulo;
		this.imagem = imagem;
		this.texto = texto;
	}

	public String getIdNoticia() {
		return idNoticia;
	}

	public void setIdNoticia(String idNoticia) {
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
