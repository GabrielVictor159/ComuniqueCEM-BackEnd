package com.comunique.comunique.dto;

import java.io.Serializable;

import com.comunique.comunique.model.Usuarios;

public class ChatDTO implements Serializable{
	private static final long serialVersionUID = 1l;
	
	private Usuarios usuario1;
	
	private Usuarios usuario2;

	
	public ChatDTO(Usuarios usuario1, Usuarios usuario2) {
		super();
		this.usuario1 = usuario1;
		this.usuario2 = usuario2;
	}

	public ChatDTO() {
		super();
	}

	public Usuarios getUsuario1() {
		return usuario1;
	}

	public void setUsuario1(Usuarios usuario1) {
		this.usuario1 = usuario1;
	}

	public Usuarios getUsuario2() {
		return usuario2;
	}

	public void setUsuario2(Usuarios usuario2) {
		this.usuario2 = usuario2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ChatDTO [usuario1=" + usuario1 + ", usuario2=" + usuario2 + "]";
	}
}

