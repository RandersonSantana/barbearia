package com.barbearia.services;

import com.barbearia.dtos.ServicoDTO;
import com.barbearia.exceptions.BusinesException;
import com.barbearia.models.Servico;
import com.barbearia.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {
    public static final String MSG_SERVICO = "Servico não encontrado";

    @Autowired
    private ServicoRepository servicoRepository;

    public ServicoDTO cadastrarServico(ServicoDTO servicoDTO){
        Servico servico = converterServicoDTOParaServico(servicoDTO);
        servico = servicoRepository.save(servico);
        return converterServicoParaServicoDTO(servico);
    }

    public ServicoDTO converterServicoParaServicoDTO(Optional<Servico> servico){
        ServicoDTO servicoDTO = new ServicoDTO(servico.getId(),
                servico.getNome(),
                servico.getDescricao(),
                servico.getPreco());
        return servicoDTO;
    }
    public Servico converterServicoDTOParaServico(ServicoDTO servicoDTO){
        Servico servico = new Servico(servicoDTO.getId(),
                servicoDTO.getNome(),
                servicoDTO.getDescricao(),
                servicoDTO.getPreco());
        return servico;
    }
    public List<ServicoDTO> buscarTodosServico(){
        List<Servico> servicoList = servicoRepository.findAll();
        List<ServicoDTO> servicoDTO = new ArrayList<>();
        for (Servico servico : servicoList){
            servicoDTO.add(new ServicoDTO(servico.getId(), servico.getNome(), servico.getDescricao(), servico.getPreco()));
        }
        return servicoDTO;
    }

    public ServicoDTO buscarServicoPorId(Long id){
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new BusinesException(MSG_SERVICO));
        return converterServicoParaServicoDTO(servico);
    }
    public  ServicoDTO atualizarServico( ServicoDTO servicoDTO){
        servicoRepository.findById(servicoDTO.getId())
                .orElseThrow(() -> new BusinesException(MSG_SERVICO));

        Servico servico = converterServicoDTOParaServico(servicoDTO);
        servicoRepository.save(servico);
        return converterServicoParaServicoDTO(servico);
    }
    public void deletarServico(Long id){
        servicoRepository.deleteById(id);
    }
}