package com.comunique.dto;

import javax.validation.constraints.NotBlank;

public class InstituicoesDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String senha;

    @NotBlank
    private String senhaProfessores;

    public InstituicoesDTO() {
    }

    public InstituicoesDTO(String nome, String senha, String senhaProfessores) {
        this.nome = nome;
        this.senha = senha;
        this.senhaProfessores = senhaProfessores;
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

    public String getSenhaProfessores() {
        return this.senhaProfessores;
    }

    public void setSenhaProfessores(String senhaProfessores) {
        this.senhaProfessores = senhaProfessores;
    }

    @Override
    public String toString() {
        return "{" +
                " nome='" + getNome() + "'" +
                ", senha='" + getSenha() + "'" +
                ", senhaProfessores='" + getSenhaProfessores() + "'" +
                "}";
    }

}
