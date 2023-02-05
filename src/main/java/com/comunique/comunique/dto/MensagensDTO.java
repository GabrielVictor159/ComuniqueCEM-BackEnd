package com.comunique.comunique.dto;

import java.io.Serializable;
import java.util.Date;

import com.comunique.comunique.model.Chat;

public class MensagensDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String idMensagem;
    private String usuarioEnviou;
    private String mensagem;
    private Date dataMensagem;
    public Chat chat;
    
    
   

    public MensagensDTO() {
		super();
	}

	public MensagensDTO(String idMensagem, String usuarioEnviou, String mensagem, Date dataMensagem, Chat chat) {
		super();
		this.idMensagem = idMensagem;
		this.usuarioEnviou = usuarioEnviou;
		this.mensagem = mensagem;
		this.dataMensagem = dataMensagem;
		this.chat = chat;
	}
    
	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public String getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(String idMensagem) {
        this.idMensagem = idMensagem;
    }

    public String getUsuarioEnviou() {
        return usuarioEnviou;
    }

    public void setUsuarioEnviou(String usuarioEnviou) {
        this.usuarioEnviou = usuarioEnviou;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataMensagem() {
        return dataMensagem;
    }

    public void setDataMensagem(Date dataMensagem) {
        this.dataMensagem = dataMensagem;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

	@Override
	public String toString() {
		return "MensagensDTO [idMensagem=" + idMensagem + ", usuarioEnviou=" + usuarioEnviou + ", mensagem=" + mensagem
				+ ", dataMensagem=" + dataMensagem + ", chat=" + chat + "]";
	}

    
}

