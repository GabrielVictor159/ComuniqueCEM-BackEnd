package com.comunique.dto;

import javax.validation.constraints.NotBlank;

public class AdminsDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String senha;

    @NotBlank

    public AdminsDTO() {
    }

    public AdminsDTO(String nome, String senha) {
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
