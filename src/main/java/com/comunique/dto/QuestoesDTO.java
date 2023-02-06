package com.comunique.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

public class QuestoesDTO {
	@NotBlank
    private UUID idQuestao;
	@NotBlank
    private String Titulo;
	@NotBlank
    private String Resposta1;
	@NotBlank
    private String Resposta2;
	@NotBlank
    private String Resposta3;
	@NotBlank
    private String Resposta4;
	@NotBlank
    private String RespostaCorreta;
	public QuestoesDTO(@NotBlank UUID idQuestao, @NotBlank String titulo, @NotBlank String resposta1,
			@NotBlank String resposta2, @NotBlank String resposta3, @NotBlank String resposta4,
			@NotBlank String respostaCorreta) {
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


  
}

