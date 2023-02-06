package com.comunique.comunique.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.comunique.comunique.model.Chat;


public class MensagensDTO {
    @NotBlank
    private UUID idMensagens;
    @NotBlank
	private UUID usuarioEnviou;
	@NotBlank
	private String mensagem;
    @NotBlank
	private Chat chat;
	public MensagensDTO(@NotBlank UUID idMensagens, @NotBlank UUID usuarioEnviou, @NotBlank String mensagem,
			@NotBlank Chat chat) {
		super();
		this.idMensagens = idMensagens;
		this.usuarioEnviou = usuarioEnviou;
		this.mensagem = mensagem;
		this.chat = chat;
	}
	public UUID getIdMensagens() {
		return idMensagens;
	}
	public void setIdMensagens(UUID idMensagens) {
		this.idMensagens = idMensagens;
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
		return "MensagensDTO [idMensagens=" + idMensagens + ", usuarioEnviou=" + usuarioEnviou + ", mensagem="
				+ mensagem + ", chat=" + chat + "]";
	}
    
    
	
    
	
}

