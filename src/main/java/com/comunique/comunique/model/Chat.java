package com.comunique.comunique.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="chat")
public class Chat implements Serializable{
	private static final long serialVersionUID = 1l;
	
	@Id
	@ManyToOne
	@JoinColumn(
			name="nomeUsuario1"
			)
	private Usuarios usuario1;
	
	@Id
	@ManyToOne
	@JoinColumn(
			name="nomeUsuario2"
			)
	private Usuarios usuario2;

	
	
	public Chat(Usuarios usuario1, Usuarios usuario2) {
		super();
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Chat [usuario1=" + usuario1 + ", usuario2=" + usuario2 + "]";
	}
	
	
	

}
