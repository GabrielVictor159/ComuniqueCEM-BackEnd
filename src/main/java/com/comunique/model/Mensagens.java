package com.comunique.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mensagens")
public class Mensagens implements Serializable {
	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idMensagens;

	@Column(nullable = false)
	private UUID usuarioEnviou;

	@Column(nullable = false)
	private String mensagem;

	@Column(nullable = false)
	private Date dataMensagem;

	@ManyToOne
	@JoinColumn(name = "chat")
	private Chat chat;

	public Mensagens(UUID idMensagens, UUID usuarioEnviou, String mensagem, Chat chat) {
		super();
		this.idMensagens = idMensagens;
		this.usuarioEnviou = usuarioEnviou;
		this.mensagem = mensagem;
		this.dataMensagem = new Date();
		this.chat = chat;
	}

	public Mensagens() {
		super();
		this.dataMensagem = new Date();
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

	public Date getDataMensagem() {
		return dataMensagem;
	}

	public void setDataMensagem(Date dataMensagem) {
		this.dataMensagem = dataMensagem;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Mensagens{" +
				"idMensagens=" + idMensagens +
				", usuarioEnviou=" + usuarioEnviou +
				", mensagem='" + mensagem + '\'' +
				", dataMensagem=" + dataMensagem +
				", chat=" + chat +
				'}';
	}
}
