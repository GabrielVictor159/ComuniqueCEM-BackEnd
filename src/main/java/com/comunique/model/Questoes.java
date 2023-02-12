package com.comunique.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	public Questoes() {
		super();
	}

	public Questoes(UUID idQuestao, String titulo, String resposta1, String resposta2, String resposta3,
			String resposta4, String respostaCorreta) {
		super();
		this.idQuestao = idQuestao;
		Titulo = titulo;
		Resposta1 = resposta1;
		Resposta2 = resposta2;
		Resposta3 = resposta3;
		Resposta4 = resposta4;
		RespostaCorreta = respostaCorreta;
	}

	public UUID getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(UUID idQuestao) {
		this.idQuestao = idQuestao;
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getResposta1() {
		return Resposta1;
	}

	public void setResposta1(String resposta1) {
		Resposta1 = resposta1;
	}

	public String getResposta2() {
		return Resposta2;
	}

	public void setResposta2(String resposta2) {
		Resposta2 = resposta2;
	}

	public String getResposta3() {
		return Resposta3;
	}

	public void setResposta3(String resposta3) {
		Resposta3 = resposta3;
	}

	public String getResposta4() {
		return Resposta4;
	}

	public void setResposta4(String resposta4) {
		Resposta4 = resposta4;
	}

	public String getRespostaCorreta() {
		return RespostaCorreta;
	}

	public void setRespostaCorreta(String respostaCorreta) {
		RespostaCorreta = respostaCorreta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Questoes [idQuestao=" + idQuestao + ", Titulo=" + Titulo + ", Resposta1=" + Resposta1 + ", Resposta2="
				+ Resposta2 + ", Resposta3=" + Resposta3 + ", Resposta4=" + Resposta4 + ", RespostaCorreta="
				+ RespostaCorreta + "]";
	}

}
