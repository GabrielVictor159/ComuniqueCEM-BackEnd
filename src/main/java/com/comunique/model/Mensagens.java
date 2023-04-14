package com.comunique.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

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
public class Mensagens extends RepresentationModel<Mensagens> implements Serializable {
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

	@Column(nullable = false)
	private boolean lida;

	@Column(nullable = false)
	private boolean entregue;

	@Column(nullable = false)
	private boolean isfile;

	@Column(nullable = false)
	private boolean deletada;

	public Mensagens() {
		super();
		this.dataMensagem = new Date();
		this.deletada = false;
	}

	public Mensagens(UUID idMensagens, UUID usuarioEnviou, String mensagem, Date dataMensagem, Chat chat, boolean lida,
			boolean entregue, boolean isfile) {
		super();
		this.idMensagens = idMensagens;
		this.usuarioEnviou = usuarioEnviou;
		this.mensagem = mensagem;
		this.dataMensagem = new Date();
		this.chat = chat;
		this.lida = lida;
		this.entregue = entregue;
		this.isfile = isfile;
		this.deletada = false;
	}

	public UUID getIdMensagens() {
		return this.idMensagens;
	}

	public void setIdMensagens(UUID idMensagens) {
		this.idMensagens = idMensagens;
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

	public Date getDataMensagem() {
		return this.dataMensagem;
	}

	public void setDataMensagem(Date dataMensagem) {
		this.dataMensagem = dataMensagem;
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

	public boolean isDeletada() {
		return this.deletada;
	}

	public boolean getDeletada() {
		return this.deletada;
	}

	public void setDeletada(boolean deletada) {
		this.deletada = deletada;
	}

	@Override
	public String toString() {
		return "{" +
				" idMensagens='" + getIdMensagens() + "'" +
				", usuarioEnviou='" + getUsuarioEnviou() + "'" +
				", mensagem='" + getMensagem() + "'" +
				", dataMensagem='" + getDataMensagem() + "'" +
				", chat='" + getChat() + "'" +
				", lida='" + isLida() + "'" +
				", entregue='" + isEntregue() + "'" +
				", isfile='" + isIsfile() + "'" +
				", deletada='" + isDeletada() + "'" +
				"}";
	}

}