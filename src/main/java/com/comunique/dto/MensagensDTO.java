package com.comunique.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.comunique.model.Chat;

public class MensagensDTO {
	@NotBlank
	private UUID usuarioEnviou;
	@NotBlank
	private String mensagem;
	@NotBlank
	private Chat chat;
	@NotBlank
	private boolean lida;
	@NotBlank
	private boolean entregue;
	@NotBlank
	private boolean isfile;

	public MensagensDTO() {
	}

	public MensagensDTO(UUID usuarioEnviou, String mensagem, Chat chat, boolean lida, boolean entregue,
			boolean isfile) {
		this.usuarioEnviou = usuarioEnviou;
		this.mensagem = mensagem;
		this.chat = chat;
		this.lida = lida;
		this.entregue = entregue;
		this.isfile = isfile;
	}

	public UUID getUsuarioEnviou() {
		return this.usuarioEnviou;
	}

	public void setUsuarioEnviou(UUID usuarioEnviou) {
		this.usuarioEnviou = usuarioEnviou;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Chat getChat() {
		return this.chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public boolean isLida() {
		return this.lida;
	}

	public boolean getLida() {
		return this.lida;
	}

	public void setLida(boolean lida) {
		this.lida = lida;
	}

	public boolean isEntregue() {
		return this.entregue;
	}

	public boolean getEntregue() {
		return this.entregue;
	}

	public void setEntregue(boolean entregue) {
		this.entregue = entregue;
	}

	public boolean isIsfile() {
		return this.isfile;
	}

	public boolean getIsfile() {
		return this.isfile;
	}

	public void setIsfile(boolean isfile) {
		this.isfile = isfile;
	}

	@Override
	public String toString() {
		return "{" +
				" usuarioEnviou='" + getUsuarioEnviou() + "'" +
				", mensagem='" + getMensagem() + "'" +
				", chat='" + getChat() + "'" +
				", lida='" + isLida() + "'" +
				", entregue='" + isEntregue() + "'" +
				", isfile='" + isIsfile() + "'" +
				"}";
	}

}
