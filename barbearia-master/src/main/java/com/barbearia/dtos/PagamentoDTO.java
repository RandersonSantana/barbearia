package com.barbearia.dtos;

public class PagamentoDTO {
    private Long id;

    private Double valor;

    private String metodo;

    private String status;

    private Long agendamentoId;

    public PagamentoDTO(){}

    public PagamentoDTO(Long id, Double valor, String metodo, String status, Long agendamentoId) {
        this.id = id;
        this.valor = valor;
        this.metodo = metodo;
        this.status = status;
        this.agendamentoId = agendamentoId;
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

    public Long getAgendamentoId() {
        return agendamentoId;
    }

    public void setAgendamentoId(Long agendamentoId) {
        this.agendamentoId = agendamentoId;
    }
}
