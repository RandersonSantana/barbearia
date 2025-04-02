package com.barbearia.services;

import com.barbearia.dtos.AgendamentoDTO;
import com.barbearia.dtos.BarbeiroDTO;
import com.barbearia.exceptions.BusinesException;
import com.barbearia.models.Agendamento;
import com.barbearia.models.Barbeiro;
import com.barbearia.repositories.BarbeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BarbeiroService {
    private static final String MSG_BARBEIRO = "Barbeiro não encontrado";

    @Autowired
    private BarbeiroRepository barbeiroRepository;


    public BarbeiroDTO converterBarbeiroParaBarbeiroDTO(Barbeiro barbeiro){
        BarbeiroDTO barbeiroDTO = new BarbeiroDTO(barbeiro.getId(),
                barbeiro.getNome(),
                barbeiro.getTelefone(),
                barbeiro.getEmail(),
                barbeiro.isAtivo(),
                barbeiro.getPerfis());

        return barbeiroDTO;
    }
    public Barbeiro converterBarbeiroDTOParaBarbeiro(BarbeiroDTO barbeiroDTO){
        Barbeiro barbeiro = new Barbeiro(barbeiroDTO.getId(),
                barbeiroDTO.getNome(),
                barbeiroDTO.getTelefone(),
                barbeiroDTO.getEmail(),
                barbeiroDTO.isAtivo(),
                barbeiroDTO.getPerfis());
        return barbeiro;
    }
    public BarbeiroDTO cadastrarBarbeiro(BarbeiroDTO barbeiroDTO){
        Barbeiro barbeiro = converterBarbeiroDTOParaBarbeiro(barbeiroDTO);
        barbeiro = barbeiroRepository.save(barbeiro);
        return converterBarbeiroParaBarbeiroDTO(barbeiro);
    }

    public BarbeiroDTO atualizarBarbeiro(BarbeiroDTO barbeiroDTO){
        barbeiroRepository.findById(barbeiroDTO.getId())
                .orElseThrow(() -> new BusinesException(MSG_BARBEIRO));

        Barbeiro barbeiro = converterBarbeiroDTOParaBarbeiro(barbeiroDTO);
        barbeiroRepository.save(barbeiro);
        return converterBarbeiroParaBarbeiroDTO(barbeiro);
    }

    public List<BarbeiroDTO> listarTodosBarbeiros() {
        List<Barbeiro> barbeiros = barbeiroRepository.findAll();
        List<BarbeiroDTO> barbeiroDTOs = new ArrayList<>();
        for (Barbeiro barbeiro : barbeiros) {
            barbeiroDTOs.add(converterBarbeiroParaBarbeiroDTO(barbeiro));
        }
        return barbeiroDTOs;
    }

    public BarbeiroDTO buscarBarbeiroPorId(Long id){
        Barbeiro barbeiro = barbeiroRepository.findById(id)
                .orElseThrow(() -> new BusinesException(MSG_BARBEIRO));
        return converterBarbeiroParaBarbeiroDTO(barbeiro);
    }
    public BarbeiroDTO buscarBarbeiroPorNome(String nome){
        Barbeiro barbeiro = barbeiroRepository.findByNome(nome)
                .orElseThrow(() -> new BusinesException(MSG_BARBEIRO));
        return converterBarbeiroParaBarbeiroDTO(barbeiro);
    }

    public  void deletarBarbeiro(Long id){
        barbeiroRepository.deleteById(id);
    }

}
