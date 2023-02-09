package com.comunique.dto;

import com.comunique.model.Usuarios;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class CronogramaDTO{

	
	@NotBlank
	private Date dataAtividade;
	
	@NotBlank
	private String cor;
	@NotBlank
	private int prazo;
	
	@NotBlank
	private String atividade;

	@NotBlank
	private Usuarios usuario;

	public CronogramaDTO(Date dataAtividade, String cor, int prazo, String atividade, Usuarios usuario) {
		this.dataAtividade = dataAtividade;
		this.cor = cor;
		this.prazo = prazo;
		this.atividade = atividade;
		this.usuario = usuario;
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

	@Override
	public String toString() {
		return "CronogramaDTO{" +
				"dataAtividade=" + dataAtividade +
				", cor='" + cor + '\'' +
				", prazo=" + prazo +
				", atividade='" + atividade + '\'' +
				", usuario=" + usuario +
				'}';
	}
}

