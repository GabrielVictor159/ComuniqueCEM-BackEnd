package com.comunique.comunique.dto;

import java.io.Serializable;
import java.util.Date;

import com.comunique.comunique.model.Usuarios;

public class CronogramaDTO implements Serializable {
	private static final long serialVersionUID = 1l;
	
	private String idAtividade;
	private Date dataAtividade;
	private String cor;
	private int prazo;
	private String atividade;
	private Usuarios usuario;
	
	
	public CronogramaDTO(String idAtividade, Date dataAtividade, String cor, int prazo, String atividade,
			Usuarios usuario) {
		super();
		this.idAtividade = idAtividade;
		this.dataAtividade = dataAtividade;
		this.cor = cor;
		this.prazo = prazo;
		this.atividade = atividade;
		this.usuario = usuario;
	}

	public CronogramaDTO() {
		super();
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
		return "CronogramaDTO [idAtividade=" + idAtividade + ", dataAtividade=" + dataAtividade + ", cor=" + cor
				+ ", prazo=" + prazo + ", atividade=" + atividade + ", usuario=" + usuario + "]";
	}
	
}

