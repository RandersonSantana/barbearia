package com.barbearia.services;

import com.barbearia.dtos.AgendamentoDTO;
import com.barbearia.dtos.BarbeiroDTO;
import com.barbearia.dtos.ServicoDTO;
import com.barbearia.dtos.UsuarioDTO;
import com.barbearia.exceptions.BusinesException;
import com.barbearia.models.Agendamento;
import com.barbearia.models.Barbeiro;
import com.barbearia.models.Servico;

import com.barbearia.models.Usuario;
import com.barbearia.repositories.AgendamentoRepository;
import com.barbearia.repositories.BarbeiroRepository;
import com.barbearia.repositories.ServicoRepository;
import com.barbearia.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public AgendamentoDTO converterAgendamentoParaDTO(Agendamento agendamento) {
        BarbeiroDTO barbeiroDTO = barbeiroService.converterBarbeiroParaBarbeiroDTO(
                agendamento.getBarbeiro());
        ServicoDTO servicoDTO = servicoService.converterServicoParaServicoDTO
                (agendamento.getServico());
        UsuarioDTO usuarioDTO = usuarioService.converterUsuarioParaUsuarioDTO
                (agendamento.getUsuario());

        AgendamentoDTO agendamentoDTO = new AgendamentoDTO(agendamento.getId(),
                agendamento.getData(),
                agendamento.getHora(),
                usuarioDTO,
                barbeiroDTO,
                servicoDTO);
        return agendamentoDTO;
    }

    public Agendamento converterAgendamentoDTOParaAgendamento(AgendamentoDTO agendamentoDTO) {

        Usuario usuario = usuarioService.converterUsuarioDTOParaUsuario
                (agendamentoDTO.getUsuario());

        Barbeiro barbeiro = barbeiroService.converterBarbeiroDTOParaBarbeiro
                (agendamentoDTO.getBarbeiro());

        Servico servico = servicoService.converterServicoDTOParaServico
                (agendamentoDTO.getServico());

        Agendamento agendamento = new Agendamento(agendamentoDTO.getId(),
                agendamentoDTO.getData(),
                agendamentoDTO.getHora(),
                usuario,
                barbeiro,
                servico);
        return agendamento;
    }

    public AgendamentoDTO cadastrarAgendamento(AgendamentoDTO agendamentoDTO) {
        Optional<Usuario> usuario = usuarioRepository.findById(agendamentoDTO.getUsuario().getId());
        Optional<Barbeiro> barbeiro = barbeiroRepository.findById(agendamentoDTO.getBarbeiro().getId());
        Optional<Servico> servico = servicoRepository.findById(agendamentoDTO.getServico().getId());

        agendamentoDTO.setUsuario(usuarioService.converterUsuarioParaUsuarioDTO(usuario));
        agendamentoDTO.setBarbeiro(barbeiroService.converterBarbeiroParaBarbeiroDTO(barbeiro));
        agendamentoDTO.setServico(servicoService.converterServicoParaServicoDTO(servico));
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
