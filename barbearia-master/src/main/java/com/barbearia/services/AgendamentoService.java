package com.barbearia.services;

import com.barbearia.dtos.AgendamentoDTO;
import com.barbearia.dtos.UsuarioDTO;
import com.barbearia.exceptions.BusinesException;
import com.barbearia.models.Agendamento;
import com.barbearia.models.Barbeiro;
import com.barbearia.models.Servico;

import com.barbearia.models.Usuario;
import com.barbearia.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    private static final String MSG_AGENDAMENTO = "Agendamento não encontrado";

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private BarbeiroService barbeiroService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private UsuarioService usuarioService;

    public AgendamentoDTO converterAgendamentoParaDTO(Agendamento agendamento) {
        AgendamentoDTO agendamentoDTO = new AgendamentoDTO();
        agendamentoDTO.setId(agendamento.getId());
        agendamentoDTO.setHora(agendamento.getHora());
        return agendamentoDTO;
    }

    public Agendamento converterAgendamentoDTOParaAgendamento(AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(agendamentoDTO.getId());
        agendamento.setData(agendamentoDTO.getData());
        agendamento.setHora(agendamentoDTO.getHora());

        Barbeiro barbeiro = barbeiroService.buscarBarbeiroPorId(agendamentoDTO.getBarbeiroId());
        agendamento.setBarbeiro(barbeiro);

        Servico servico = servicoService.buscarServicoPorId(agendamentoDTO.getServicoId());
        agendamento.setServico(servico);

        Usuario usuario = usuarioService.buscarUsuarioPorId(agendamentoDTO.getUsuarioId());
        agendamento.setUsuario(usuario);
        return agendamento;
    }

    public AgendamentoDTO cadastrarAgendamento(AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = converterAgendamentoDTOParaAgendamento(agendamentoDTO);
        agendamento = agendamentoRepository.save(agendamento);
        return converterAgendamentoParaDTO(agendamento);
    }

    public AgendamentoDTO atualizarAgendamento(AgendamentoDTO agendamentoDTO){
        agendamentoRepository.findById(agendamentoDTO.getId())
                .orElseThrow(() -> new BusinesException(MSG_AGENDAMENTO));

        Agendamento agendamento = converterAgendamentoDTOParaAgendamento(agendamentoDTO);
        agendamentoRepository.save(agendamento);

        return converterAgendamentoParaDTO(agendamento);
    }

    public List<AgendamentoDTO> listarTodosAgendamentos() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.stream()
                .map(this::converterAgendamentoParaDTO)
                .collect(Collectors.toList());
    }

    public AgendamentoDTO buscarAgendamentoPorId(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new BusinesException(MSG_AGENDAMENTO));
        return converterAgendamentoParaDTO(agendamento);
    }

    public void deletarAgendamento(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new BusinesException(MSG_AGENDAMENTO));
        agendamentoRepository.delete(agendamento);
    }
}
