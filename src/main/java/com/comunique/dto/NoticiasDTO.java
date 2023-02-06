package com.comunique.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class NoticiasDTO{
	@NotBlank
private UUID idNoticia;
	@NotBlank
private String titulo;
	@NotBlank
private String imagem;
	@NotBlank
private String texto;

public NoticiasDTO(UUID idNoticia, String titulo, String imagem, String texto) {
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

@Override
public String toString() {
	return "NoticiasDTO [idNoticia=" + idNoticia + ", titulo=" + titulo + ", imagem=" + imagem + ", texto=" + texto
			+ "]";
}




}
