package com.comunique.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.comunique.functions.MD5Encoder;
import com.comunique.model.enums.typeUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuariosSolicitacoes")
public class UsuariosSolicitacoes implements Serializable {
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nomeUsuario;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private typeUsuario tipoUsuario;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String fotoPerfil;

    @Column(nullable = false)
    private boolean usuarioOnline;

    @ManyToOne
    @JoinColumn(name = "instituicao", nullable = false)
    private Instituicoes instituicao;

    @Column(nullable = true)
    private String senhaProvisoria;

    @Column(nullable = true)
    private Date dataSenhaProvisoria;

    @Column(nullable = false)
    private Date dataSolicitacao;

    public UsuariosSolicitacoes() {
        this.dataSolicitacao = new Date();
    }

    public UsuariosSolicitacoes(UUID id, String nomeUsuario, typeUsuario tipoUsuario, String email, String senha,
            String fotoPerfil, boolean usuarioOnline, Instituicoes instituicao) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.tipoUsuario = tipoUsuario;
        this.email = email;
        this.senha = senha;
        this.fotoPerfil = fotoPerfil;
        this.usuarioOnline = usuarioOnline;
        this.instituicao = instituicao;
        this.dataSolicitacao = new Date();
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return this.nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public typeUsuario getTipoUsuario() {
        return this.tipoUsuario;
    }

    public void setTipoUsuario(typeUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFotoPerfil() {
        return this.fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public boolean isUsuarioOnline() {
        return this.usuarioOnline;
    }

    public boolean getUsuarioOnline() {
        return this.usuarioOnline;
    }

    public void setUsuarioOnline(boolean usuarioOnline) {
        this.usuarioOnline = usuarioOnline;
    }

    public Instituicoes getInstituicao() {
        return this.instituicao;
    }

    public void setInstituicao(Instituicoes instituicao) {
        this.instituicao = instituicao;
    }

    public String getSenhaProvisoria() {
        return this.senhaProvisoria;
    }

    public void setSenhaProvisoria(String senhaProvisoria) {
        this.senhaProvisoria = senhaProvisoria;
    }

    public Date getDataSenhaProvisoria() {
        return this.dataSenhaProvisoria;
    }

    public void setDataSenhaProvisoria(Date dataSenhaProvisoria) {
        this.dataSenhaProvisoria = dataSenhaProvisoria;
    }

    public Date getDataSolicitacao() {
        return this.dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", nomeUsuario='" + getNomeUsuario() + "'" +
                ", tipoUsuario='" + getTipoUsuario() + "'" +
                ", email='" + getEmail() + "'" +
                ", senha='" + getSenha() + "'" +
                ", fotoPerfil='" + getFotoPerfil() + "'" +
                ", usuarioOnline='" + isUsuarioOnline() + "'" +
                ", instituicao='" + getInstituicao() + "'" +
                ", senhaProvisoria='" + getSenhaProvisoria() + "'" +
                ", dataSenhaProvisoria='" + getDataSenhaProvisoria() + "'" +
                ", dataSolicitacao='" + getDataSolicitacao() + "'" +
                "}";
    }

}
