//package com.barbearia.controllers;
//
//import com.barbearia.dtos.PagamentoDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/pagamentos")
//public class PagamentoController {
//    @Autowired
//    private pagamentoService pagamentoService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<PagamentoDTO> buscarPagamentoPorId(@PathVariable Long id) {
//        return ResponseEntity.ok(pagamentoService.buscarPagamentoPorId(id));
//    }
//
//    @PostMapping
//    public ResponseEntity<PagamentoDTO> cadastrarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
//        PagamentoDTO pagamento = pagamentoService.cadastrarPagamento(pagamentoDTO);
//        return ResponseEntity.ok(pagamento);
//    }
//
//    @PutMapping
//    public ResponseEntity<PagamentoDTO> atualizarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
//        return ResponseEntity.ok(pagamentoService.atualizarPagamento(pagamentoDTO));
//    }
//
//    @DeleteMapping()
//    public ResponseEntity<Void> deletarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
//        pagamentoService.deletarPagamento(pagamentoDTO.getId());
//        return ResponseEntity.noContent().build();
//    }
//}