package com.comunique.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admins implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAdmin;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "instituicao", nullable = false)
    private Instituicoes instituicao;

    public Admins() {
    }

    public Admins(UUID idAdmin, String nome, String senha, Instituicoes instituicao) {
        this.idAdmin = idAdmin;
        this.nome = nome;
        this.senha = senha;
        this.instituicao = instituicao;
    }

    public UUID getIdAdmin() {
        return this.idAdmin;
    }

    public void setIdAdmin(UUID idAdmin) {
        this.idAdmin = idAdmin;
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
        this.senha = senha;
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
                " idAdmin='" + getIdAdmin() + "'" +
                ", nome='" + getNome() + "'" +
                ", senha='" + getSenha() + "'" +
                ", instituicao='" + getInstituicao() + "'" +
                "}";
    }

}
