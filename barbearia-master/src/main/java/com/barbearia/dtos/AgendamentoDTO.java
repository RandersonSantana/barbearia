package com.barbearia.dtos;

import com.barbearia.models.Usuario;

import java.time.LocalTime;
import java.util.Date;

public class AgendamentoDTO {
    private Long id;
    private Date data;
    private LocalTime hora;
    private Long usuarioId;
    private Long barbeiroId;
    private Long servicoId;

    public AgendamentoDTO() {}

    public AgendamentoDTO(Long id, Date data, LocalTime hora, Long usuarioId, Long barbeiroId, Long servicoId) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.usuarioId = usuarioId;
        this.barbeiroId = barbeiroId;
        this.servicoId = servicoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getBarbeiroId() {
        return barbeiroId;
    }

    public void setBarbeiroId(Long barbeiroId) {
        this.barbeiroId = barbeiroId;
    }

    public Long getServicoId() {
        return servicoId;
    }

    public void setServicoId(Long servicoId) {
        this.servicoId = servicoId;
    }

}
