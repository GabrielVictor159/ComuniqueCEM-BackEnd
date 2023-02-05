package com.comunique.comunique.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mensagens")
public class Mensagens implements Serializable {
	private static final long serialVersionUID = 1l;
	
	@Id
	@Column
	private String idMensagem;
	
	@Column(nullable=false)
	private String usuarioEnviou;
	
	@Column(nullable=false)
	private String mensagem;
	
	@Column(nullable=false)
	private Date dataMensagem;
	
	@ManyToOne
	@JoinColumn(
			name="chat"
			)
	private Chat chat;

	
	
	public Mensagens(String idMensagem, String usuarioEnviou, String mensagem, Date dataMensagem, Chat chat) {
		super();
		this.idMensagem = idMensagem;
		this.usuarioEnviou = usuarioEnviou;
		this.mensagem = mensagem;
		this.dataMensagem = dataMensagem;
		this.chat = chat;
	}

	public String getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(String idMensagem) {
		this.idMensagem = idMensagem;
	}

	public String getUsuarioEnviou() {
		return usuarioEnviou;
	}

	public void setUsuarioEnviou(String usuarioEnviou) {
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
		return "Mensagens [idMensagem=" + idMensagem + ", usuarioEnviou=" + usuarioEnviou + ", mensagem=" + mensagem
				+ ", dataMensagem=" + dataMensagem + ", chat=" + chat + "]";
	}
	
	
}
