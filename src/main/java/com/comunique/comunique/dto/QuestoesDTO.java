package com.comunique.comunique.dto;

import java.io.Serializable;

public class QuestoesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idQuestao;
    private String Titulo;
    private String Resposta1;
    private String Resposta2;
    private String Resposta3;
    private String Resposta4;
    private String RespostaCorreta;

    public QuestoesDTO() {}

    public QuestoesDTO(String idQuestao, String titulo, String resposta1, String resposta2, String resposta3,
            String resposta4, String respostaCorreta) {
        this.idQuestao = idQuestao;
        Titulo = titulo;
        Resposta1 = resposta1;
        Resposta2 = resposta2;
        Resposta3 = resposta3;
        Resposta4 = resposta4;
        RespostaCorreta = respostaCorreta;
    }

    public String getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(String idQuestao) {
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

    @Override
    public String toString() {
        return "QuestoesDTO [idQuestao=" + idQuestao + ", Titulo=" + Titulo + ", Resposta1=" + Resposta1
                + ", Resposta2=" + Resposta2 + ", Resposta3=" + Resposta3 + ", Resposta4=" + Resposta4
                + ", RespostaCorreta=" + RespostaCorreta + "]";
    }
}

