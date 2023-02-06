package com.comunique.comunique.model;

import java.io.Serializable;
import java.util.UUID;


import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="chat")
public class Chat implements Serializable{
	private static final long serialVersionUID = 1l;
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(nullable=false)
	private UUID idChat;
	@ManyToOne
	@JoinColumn(
			name="idUsuario1"
			)
	private Usuarios usuario1;
	
	@ManyToOne
	@JoinColumn(
			name="idUsuario2"
			)
	private Usuarios usuario2;

	public UUID getIdChat() {
		return idChat;
	}

	public void setIdChat(UUID idChat) {
		this.idChat = idChat;
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

	public Chat(UUID idChat, Usuarios usuario1, Usuarios usuario2) {
		super();
		this.idChat = idChat;
		this.usuario1 = usuario1;
		this.usuario2 = usuario2;
	}

	public Chat() {
		super();
	}

	@Override
	public String toString() {
		return "Chat [idChat=" + idChat + ", usuario1=" + usuario1 + ", usuario2=" + usuario2 + "]";
	}

	
	
	
	
	

}
