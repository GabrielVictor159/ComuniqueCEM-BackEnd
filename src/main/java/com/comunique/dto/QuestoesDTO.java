package com.comunique.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class QuestoesDTO {
	@NotNull
	private String Titulo;
	@NotNull
	private String Resposta1;
	@NotNull
	private String Resposta2;
	@NotNull
	private String Resposta3;
	@NotNull
	private String Resposta4;
	@NotNull
	private String RespostaCorreta;

	public QuestoesDTO()
	{
		
	}
	public QuestoesDTO(@NotNull String titulo, @NotNull String resposta1,
			@NotNull String resposta2, @NotNull String resposta3, @NotNull String resposta4,
			@NotNull String respostaCorreta) {
		super();
		Titulo = titulo;
		Resposta1 = resposta1;
		Resposta2 = resposta2;
		Resposta3 = resposta3;
		Resposta4 = resposta4;
		RespostaCorreta = respostaCorreta;
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
	@Override
	public String toString() {
		return "QuestoesDTO [Titulo=" + Titulo + ", Resposta1=" + Resposta1 + ", Resposta2=" + Resposta2
				+ ", Resposta3=" + Resposta3 + ", Resposta4=" + Resposta4 + ", RespostaCorreta=" + RespostaCorreta
				+ "]";
	}
	
}
