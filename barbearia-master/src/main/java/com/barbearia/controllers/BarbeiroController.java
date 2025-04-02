package com.barbearia.controllers;

import com.barbearia.dtos.BarbeiroDTO;
import com.barbearia.models.Agendamento;
import com.barbearia.services.BarbeiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barbeiros")
public class BarbeiroController {
    @Autowired
    private BarbeiroService barbeiroService;

    @GetMapping("/{id}")
    public ResponseEntity<BarbeiroDTO> buscarBarbeiroPorId(@PathVariable Long id){
        return ResponseEntity.ok(barbeiroService.buscarBarbeiroPorId(id));
    }
    @GetMapping
    public ResponseEntity<List<BarbeiroDTO>> buscarTodosBarbeiros(){
        return ResponseEntity.ok(barbeiroService.listarTodosBarbeiros());
    }
    @GetMapping("/buscar")
    public ResponseEntity<BarbeiroDTO> buscarBarbeiroPorNome(@RequestParam String nome){
        return ResponseEntity.ok(barbeiroService.buscarBarbeiroPorNome(nome));
    }

    @PostMapping()
    public  ResponseEntity<BarbeiroDTO> cadastrarBarbeiro(@RequestBody BarbeiroDTO barbeiroDTO) {
        BarbeiroDTO barbeiro = barbeiroService.cadastrarBarbeiro(barbeiroDTO);
        return ResponseEntity.ok(barbeiro);
    }
    @PutMapping()
    public ResponseEntity<BarbeiroDTO> atualizarBarbeiro(@RequestBody BarbeiroDTO barbeiroDTO) {
        return ResponseEntity.ok(barbeiroService.atualizarBarbeiro(barbeiroDTO));
    }
    @DeleteMapping()
    public ResponseEntity<Void> deletarBarbeiro(@RequestBody BarbeiroDTO barbeiroDTO) {
        barbeiroService.deletarBarbeiro(barbeiroDTO.getId());
        return ResponseEntity.noContent().build();
    }
}
