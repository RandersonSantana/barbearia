package com.barbearia.controllers;

import com.barbearia.dtos.AgendamentoDTO;
import com.barbearia.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> buscarAgendamentoPorId(@PathVariable Long id){
        return ResponseEntity.ok(agendamentoService.buscarAgendamentoPorId(id));
    }
    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> buscarTodosAgendamentos(){
        return ResponseEntity.ok(agendamentoService.listarTodosAgendamentos());
    }

    @PostMapping()
    public  ResponseEntity<AgendamentoDTO> cadastrarAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
        System.out.println(agendamentoDTO.getData());
        System.out.println(agendamentoDTO.getHora());
        System.out.println(agendamentoDTO.getUsuario().getId());
        System.out.println(agendamentoDTO.getBarbeiro().getId());
        System.out.println(agendamentoDTO.getServico().getId());
        AgendamentoDTO agendamento = agendamentoService.cadastrarAgendamento(agendamentoDTO);
        return ResponseEntity.ok(agendamento);
    }
    @PutMapping()
    public ResponseEntity<AgendamentoDTO> atualizarAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
        return ResponseEntity.ok(agendamentoService.atualizarAgendamento(agendamentoDTO));
    }
    @DeleteMapping()
    public ResponseEntity<Void> deletarAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
        agendamentoService.deletarAgendamento(agendamentoDTO.getId());
        return ResponseEntity.noContent().build();
    }
}
