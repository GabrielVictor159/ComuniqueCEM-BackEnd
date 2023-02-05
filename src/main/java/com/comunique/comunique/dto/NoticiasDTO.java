package com.comunique.comunique.dto;

import java.io.Serializable;

public class NoticiasDTO implements Serializable {
private static final long serialVersionUID = 1l;
private String idNoticia;

private String titulo;

private String imagem;

private String texto;



public NoticiasDTO() {
	super();
}

public NoticiasDTO(String idNoticia, String titulo, String imagem, String texto) {
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
	return "NoticiasDTO [idNoticia=" + idNoticia + ", titulo=" + titulo + ", imagem=" + imagem + ", texto=" + texto
			+ "]";
}
}
