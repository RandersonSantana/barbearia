package com.barbearia.dtos;

import com.barbearia.enums.Perfil;

import java.util.List;

public class BarbeiroDTO {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private boolean ativo;
    private List<Long> agendamentosIds;

    public BarbeiroDTO() {}

    public BarbeiroDTO(Long id, String nome, String telefone, String email, boolean ativo, List<Long> agendamentosIds) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.ativo = ativo;
        this.agendamentosIds = agendamentosIds;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Long> getAgendamentosIds() {
        return agendamentosIds;
    }

    public void setAgendamentosIds(List<Long> agendamentosIds) {
        this.agendamentosIds = agendamentosIds;
    }
}
