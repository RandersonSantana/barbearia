package com.barbearia.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "barbeiro")
public class Barbeiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    @Column(unique = true)
    private String email;

    private boolean ativo;


    @OneToMany(mappedBy = "barbeiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agendamento> agendamentos;

    public Barbeiro() {
    }

    public Barbeiro(Long id, String nome, String telefone, String email, boolean ativo, List<Agendamento> agendamentos) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.ativo = ativo;
        this.agendamentos = agendamentos;
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

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

}
