package com.barbearia.controllers;

import com.barbearia.dtos.ServicoDTO;
import com.barbearia.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {
    @Autowired
    private ServicoService servicoService;

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> buscarServicoPorId(@PathVariable Long id){
        return ResponseEntity.ok(servicoService.buscarServicoDTOPorId(id));
    }
    @GetMapping
    public ResponseEntity<List<ServicoDTO>> buscarTodosServico(){
        return ResponseEntity.ok(servicoService.buscarTodosServico());
    }
    @PostMapping
    public ResponseEntity<ServicoDTO> cadastrarServico(@RequestBody ServicoDTO servicoDTO){
        ServicoDTO servico = servicoService.cadastrarServico(servicoDTO);
        return ResponseEntity.ok(servico);
    }
    @PutMapping
    public ResponseEntity<ServicoDTO> atualizarServico(@RequestBody ServicoDTO servicoDTO){
        return ResponseEntity.ok(servicoService.atualizarServico(servicoDTO));
    }
    @DeleteMapping()
    public ResponseEntity<Void> deletarServico(@RequestBody ServicoDTO servicoDTO){
        servicoService.deletarServico(servicoDTO.getId());
        return ResponseEntity.noContent().build();
    }
}
