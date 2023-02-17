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
@Table(name = "cronograma")
public class Cronograma extends RepresentationModel<Cronograma> implements Serializable {
	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idCronograma;

	@Column(nullable = false)
	private Date dataAtividade;

	@Column(nullable = false)
	private String cor;

	@Column(nullable = false)
	private int prazo;

	@Column(nullable = false)
	private String atividade;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuarios usuario;

	public Cronograma(UUID idCronograma, Date dataAtividade, String cor, int prazo, String atividade,
			Usuarios usuario) {
		super();
		this.idCronograma = idCronograma;
		this.dataAtividade = dataAtividade;
		this.cor = cor;
		this.prazo = prazo;
		this.atividade = atividade;
		this.usuario = usuario;
	}

	public Cronograma() {
		super();
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
		return "Cronograma [idCronograma=" + idCronograma + ", dataAtividade=" + dataAtividade + ", cor=" + cor
				+ ", prazo=" + prazo + ", atividade=" + atividade + ", usuario=" + usuario + "]";
	}

}
