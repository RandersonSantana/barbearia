package com.barbearia.services;

import com.barbearia.dtos.*;
import com.barbearia.exceptions.BusinesException;
import com.barbearia.models.*;

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

    @Autowired
    private PagamentoService pagamentoService;

    public AgendamentoDTO converterAgendamentoParaDTO(Agendamento agendamento) {

        UsuarioDTO usuarioDTO = usuarioService.buscarUsuarioPorId(agendamento.getUsuario().getId());
        BarbeiroDTO barbeiroDTO = barbeiroService.buscarBarbeiroPorId(agendamento.getBarbeiro().getId());
        ServicoDTO servicoDTO = servicoService.buscarServicoPorId(agendamento.getServico().getId());
        PagamentoDTO pagamentoDTO = pagamentoService.buscarPagamentoPorId(agendamento.getPagamento().getId());

        AgendamentoDTO agendamentoDTO = new AgendamentoDTO(
                agendamento.getId(),
                agendamento.getData(),
                agendamento.getHora(),
                usuarioDTO,
                barbeiroDTO,
                servicoDTO,
                pagamentoDTO
                );

        return agendamentoDTO;
    }

    public Agendamento converterAgendamentoDTOParaAgendamento(AgendamentoDTO agendamentoDTO) {

        Usuario usuario = usuarioService.converterUsuarioDTOParaUsuario
                (agendamentoDTO.getUsuario());

        Barbeiro barbeiro = barbeiroService.converterBarbeiroDTOParaBarbeiro
                (agendamentoDTO.getBarbeiro());

        Servico servico = servicoService.converterServicoDTOParaServico
                (agendamentoDTO.getServico());

        Pagamento pagamento = pagamentoService.converterPagamentoDTOParaPagamento
                (agendamentoDTO.getPagamento());

        Agendamento agendamento = new Agendamento(agendamentoDTO.getId(),
                agendamentoDTO.getData(),
                agendamentoDTO.getHora(),
                usuario,
                barbeiro,
                servico,
                pagamento);
        return agendamento;
    }

    public AgendamentoDTO cadastrarAgendamento(AgendamentoDTO agendamentoDTO) {
        UsuarioDTO usuarioDTO = usuarioService.buscarUsuarioPorId(agendamentoDTO.getUsuario().getId());
        BarbeiroDTO barbeiroDTO = barbeiroService.buscarBarbeiroPorId(agendamentoDTO.getBarbeiro().getId());
        ServicoDTO servicoDTO = servicoService.buscarServicoPorId(agendamentoDTO.getServico().getId());


        agendamentoDTO.setUsuario(usuarioDTO);
        agendamentoDTO.setBarbeiro(barbeiroDTO);
        agendamentoDTO.setServico(servicoDTO);
        Agendamento agendamento = converterAgendamentoDTOParaAgendamento(agendamentoDTO);
        System.out.println(agendamento);
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
