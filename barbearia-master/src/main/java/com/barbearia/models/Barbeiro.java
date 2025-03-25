package com.barbearia.models;

import com.barbearia.enums.Perfil;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

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

    @Enumerated(EnumType.STRING)
    private List<Perfil> perfis;

    @OneToMany(mappedBy = "barbeiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agendamento> agendamentos;

    public Barbeiro(Long id, String nome, String telefone, String email, boolean ativo, List<Perfil> perfis) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.ativo = ativo;
        this.perfis = perfis;
        this.agendamentos = agendamentos;
    }

    public Barbeiro() {}

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

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Barbeiro barbeiro = (Barbeiro) o;
        return ativo == barbeiro.ativo && Objects.equals(id, barbeiro.id) && Objects.equals(nome, barbeiro.nome) && Objects.equals(telefone, barbeiro.telefone) && Objects.equals(email, barbeiro.email) && Objects.equals(perfis, barbeiro.perfis) && Objects.equals(agendamentos, barbeiro.agendamentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, telefone, email, ativo, perfis, agendamentos);
    }
}
