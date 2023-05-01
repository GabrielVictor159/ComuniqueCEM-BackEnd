package com.comunique.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class NoticiasDTO {
	@NotNull
	private String titulo;
	@NotNull
	private String imagem;
	@NotNull
	private String texto;

	public NoticiasDTO(String titulo, String imagem, String texto) {
		this.titulo = titulo;
		this.imagem = imagem;
		this.texto = texto;
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

	@Override
	public String toString() {
		return "NoticiasDTO{" +
				"titulo='" + titulo + '\'' +
				", imagem='" + imagem + '\'' +
				", texto='" + texto + '\'' +
				'}';
	}
}
