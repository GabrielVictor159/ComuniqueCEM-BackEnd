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
@Table(name="cronograma")
public class Cronograma implements Serializable {
	private static final long serialVersionUID = 1l;
	
	@Id
	@Column
	private String idAtividade;
	
	@Column(nullable=false)
	private Date dataAtividade;
	
	@Column(nullable=false)
	private String cor;
	
	@Column(nullable=false)
	private int prazo;
	
	@Column(nullable=false)
	private String atividade;
	
	@ManyToOne
	@JoinColumn(
			name = "nomeUsuario"
			)
	private Usuarios usuario;

	
	
	public Cronograma(String idAtividade, Date dataAtividade, String cor, int prazo, String atividade,
			Usuarios usuario) {
		super();
		this.idAtividade = idAtividade;
		this.dataAtividade = dataAtividade;
		this.cor = cor;
		this.prazo = prazo;
		this.atividade = atividade;
		this.usuario = usuario;
	}

	public String getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(String idAtividade) {
		this.idAtividade = idAtividade;
	}

	public Date getDataAtividade() {
		return dataAtividade;
	}

	public void setDataAtividade(Date dataAtividade) {
		this.dataAtividade = dataAtividade;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Cronograma [idAtividade=" + idAtividade + ", dataAtividade=" + dataAtividade + ", cor=" + cor
				+ ", prazo=" + prazo + ", atividade=" + atividade + ", usuario=" + usuario + "]";
	}

	
	
	
}
