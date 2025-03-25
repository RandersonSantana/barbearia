package com.barbearia.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "pagamentos")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    private String metodo;

    private String status;

    @OneToOne
    @JoinColumn(name = "agendamento_id", nullable = false)
    private Agendamento agendamento;

    public Pagamento() {
    }

    public Pagamento(Long id, Double valor, String metodo, String status, Agendamento agendamento) {
        this.id = id;
        this.valor = valor;
        this.metodo = metodo;
        this.status = status;
        this.agendamento = agendamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id) && Objects.equals(valor, pagamento.valor) && Objects.equals(metodo, pagamento.metodo) && Objects.equals(status, pagamento.status) && Objects.equals(agendamento, pagamento.agendamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, metodo, status, agendamento);
    }
}