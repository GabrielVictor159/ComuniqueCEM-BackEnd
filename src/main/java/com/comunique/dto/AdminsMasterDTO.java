package com.comunique.dto;

import javax.validation.constraints.NotNull;

public class AdminsMasterDTO {
    @NotNull
    private String nome;

    @NotNull
    private String senha;

    public AdminsMasterDTO() {
    }

    public AdminsMasterDTO(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;

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

    @Override
    public String toString() {
        return "{" +
                " nome='" + getNome() + "'" +
                ", senha='" + getSenha() + "'" +
                "}";
    }
}
