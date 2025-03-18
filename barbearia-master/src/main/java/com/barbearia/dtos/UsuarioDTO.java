package com.barbearia.dtos;

import com.barbearia.enums.Perfil;

import java.util.List;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private List<Perfil> perfis;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String telefone, String email, List<Perfil> perfis) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.perfis = perfis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }
}