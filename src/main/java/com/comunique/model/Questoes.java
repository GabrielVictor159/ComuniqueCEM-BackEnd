package com.comunique.model;

import java.io.Serializable;
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
@Table(name = "questoes")
public class Questoes extends RepresentationModel<Questoes> implements Serializable {
	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID idQuestao;

	@Column(nullable = false)
	private String Titulo;

	@Column(nullable = false)
	private String Resposta1;

	@Column(nullable = false)
	private String Resposta2;

	@Column(nullable = false)
	private String Resposta3;

	@Column(nullable = false)
	private String Resposta4;

	@Column(nullable = false)
	private String RespostaCorreta;

	@ManyToOne
	@JoinColumn(name = "instituicao", nullable = false)
	private Instituicoes instituicao;

	public Questoes() {
	}

	public Questoes(UUID idQuestao, String Titulo, String Resposta1, String Resposta2, String Resposta3,
			String Resposta4, String RespostaCorreta, Instituicoes instituicao) {
		this.idQuestao = idQuestao;
		this.Titulo = Titulo;
		this.Resposta1 = Resposta1;
		this.Resposta2 = Resposta2;
		this.Resposta3 = Resposta3;
		this.Resposta4 = Resposta4;
		this.RespostaCorreta = RespostaCorreta;
		this.instituicao = instituicao;
	}

	public UUID getIdQuestao() {
		return this.idQuestao;
	}

	public void setIdQuestao(UUID idQuestao) {
		this.idQuestao = idQuestao;
	}

	public String getTitulo() {
		return this.Titulo;
	}

	public void setTitulo(String Titulo) {
		this.Titulo = Titulo;
	}

	public String getResposta1() {
		return this.Resposta1;
	}

	public void setResposta1(String Resposta1) {
		this.Resposta1 = Resposta1;
	}

	public String getResposta2() {
		return this.Resposta2;
	}

	public void setResposta2(String Resposta2) {
		this.Resposta2 = Resposta2;
	}

	public String getResposta3() {
		return this.Resposta3;
	}

	public void setResposta3(String Resposta3) {
		this.Resposta3 = Resposta3;
	}

	public String getResposta4() {
		return this.Resposta4;
	}

	public void setResposta4(String Resposta4) {
		this.Resposta4 = Resposta4;
	}

	public String getRespostaCorreta() {
		return this.RespostaCorreta;
	}

	public void setRespostaCorreta(String RespostaCorreta) {
		this.RespostaCorreta = RespostaCorreta;
	}

	public Instituicoes getInstituicao() {
		return this.instituicao;
	}

	public void setInstituicao(Instituicoes instituicao) {
		this.instituicao = instituicao;
	}

	@Override
	public String toString() {
		return "{" +
				" idQuestao='" + getIdQuestao() + "'" +
				", Titulo='" + getTitulo() + "'" +
				", Resposta1='" + getResposta1() + "'" +
				", Resposta2='" + getResposta2() + "'" +
				", Resposta3='" + getResposta3() + "'" +
				", Resposta4='" + getResposta4() + "'" +
				", RespostaCorreta='" + getRespostaCorreta() + "'" +
				", instituicao='" + getInstituicao() + "'" +
				"}";
	}

}
