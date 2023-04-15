package com.comunique.dto;

import javax.validation.constraints.NotBlank;

import com.comunique.model.Usuarios;

public class ChatDTO {

	@NotBlank
	private Usuarios usuario1;

	@NotBlank
	private Usuarios usuario2;

	public ChatDTO(Usuarios usuario1, Usuarios usuario2) {
		this.usuario1 = usuario1;
		this.usuario2 = usuario2;
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

	@Override
	public String toString() {
		return "ChatDTO{" +
				"usuario1=" + usuario1 +
				", usuario2=" + usuario2 +
				'}';
	}
}
