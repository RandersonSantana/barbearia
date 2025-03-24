package com.barbearia.dtos;

import com.barbearia.models.Usuario;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;
@Getter
@Setter
@Data
public class AgendamentoDTO {
    private Long id;
    private Date data;
    private LocalTime hora;
    private UsuarioDTO usuario;
    private BarbeiroDTO barbeiro;
    private ServicoDTO servico;

    public AgendamentoDTO() {}

    public AgendamentoDTO(Long id, Date data, LocalTime hora, UsuarioDTO usuarioDTO, BarbeiroDTO barbeiroDTO, ServicoDTO servicoDTO) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.usuario = usuarioDTO;
        this.barbeiro = barbeiroDTO;
        this.servico = servicoDTO;
    }

    public BarbeiroDTO getBarbeiroId() {
        return barbeiro;
    }

    public ServicoDTO getServicoId() {
        return servico;
    }
}
