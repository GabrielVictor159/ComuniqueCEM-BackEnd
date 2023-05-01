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
@Table(name = "adminsmaster")
public class AdminsMaster implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAdmin;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String senha;

    public AdminsMaster() {
    }

    public AdminsMaster(UUID idAdmin, String nome, String senha) {
        this.idAdmin = idAdmin;
        this.nome = nome;
        this.senha = MD5Encoder.encode(senha);
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
        this.senha = MD5Encoder.encode(senha);
    }

    @Override
    public String toString() {
        return "{" +
                " idAdmin='" + getIdAdmin() + "'" +
                ", nome='" + getNome() + "'" +
                ", senha='" + getSenha() + "'" +
                "}";
    }

}
