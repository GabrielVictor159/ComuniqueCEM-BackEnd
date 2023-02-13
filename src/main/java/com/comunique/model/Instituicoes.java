package com.comunique.model;

import java.io.Serializable;
import java.util.UUID;

import com.comunique.functions.MD5Encoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "instituicoes")
public class Instituicoes implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idInstituicao;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String senhaProfessores;

    public Instituicoes() {
    }

    public Instituicoes(UUID idInstituicao, String nome, String senha, String senhaProfessores) {
        this.idInstituicao = idInstituicao;
        this.nome = nome;
        this.senha = MD5Encoder.encode(senha);
        this.senhaProfessores = MD5Encoder.encode(senhaProfessores);
    }

    public UUID getIdInstituicao() {
        return this.idInstituicao;
    }

    public void setIdInstituicao(UUID idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = MD5Encoder.encode(senha);
    }

    public String getSenhaProfessores() {
        return this.senhaProfessores;
    }

    public void setSenhaProfessores(String senhaProfessores) {
        this.senhaProfessores = MD5Encoder.encode(senhaProfessores);
    }

    @Override
    public String toString() {
        return "{" +
                " idInstituicao='" + getIdInstituicao() + "'" +
                ", nome='" + getNome() + "'" +
                ", senha='" + getSenha() + "'" +
                ", senhaProfessores='" + getSenhaProfessores() + "'" +
                "}";
    }

}
