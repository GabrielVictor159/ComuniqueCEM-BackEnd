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

	public MensagensDTO(UUID usuarioEnviou, String mensagem, Chat chat) {
		this.usuarioEnviou = usuarioEnviou;
		this.mensagem = mensagem;
		this.chat = chat;
	}

	public UUID getUsuarioEnviou() {
		return usuarioEnviou;
	}

	public void setUsuarioEnviou(UUID usuarioEnviou) {
		this.usuarioEnviou = usuarioEnviou;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	@Override
	public String toString() {
		return "MensagensDTO{" +
				"usuarioEnviou=" + usuarioEnviou +
				", mensagem='" + mensagem + '\'' +
				", chat=" + chat +
				'}';
	}
}

