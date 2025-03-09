package com.barbearia.dtos;

import java.time.LocalTime;
import java.util.Date;

public class AgendamentoDTO {
    private Long id;
    private Date data;
    private LocalTime hora;

    public AgendamentoDTO() {}

    public AgendamentoDTO(Long id, Date data, LocalTime hora) {
        this.id = id;
        this.data = data;
        this.hora = hora;
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
}
