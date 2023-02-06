package com.comunique.comunique.dto;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class CronogramaDTO{
	@NotBlank
	private UUID idCronograma;
	
	@NotBlank
	private Date dataAtividade;
	
	@NotBlank
	private String cor;
	@NotBlank
	private int prazo;
	
	@NotBlank
	private String atividade;

	public CronogramaDTO(@NotBlank UUID idCronograma, @NotBlank Date dataAtividade, @NotBlank String cor,
			@NotBlank int prazo, @NotBlank String atividade) {
		super();
		this.idCronograma = idCronograma;
		this.dataAtividade = dataAtividade;
		this.cor = cor;
		this.prazo = prazo;
		this.atividade = atividade;
	}

	public UUID getIdCronograma() {
		return idCronograma;
	}

	public void setIdCronograma(UUID idCronograma) {
		this.idCronograma = idCronograma;
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

	@Override
	public String toString() {
		return "CronogramaDTO [idCronograma=" + idCronograma + ", dataAtividade=" + dataAtividade + ", cor=" + cor
				+ ", prazo=" + prazo + ", atividade=" + atividade + "]";
	}
	
	
	
	
}

