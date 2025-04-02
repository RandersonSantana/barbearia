package com.barbearia.controllers;

import com.barbearia.dtos.ProdutoDTO;
import com.barbearia.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/id/{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> buscarTodosProdutos(){
        return ResponseEntity.ok(produtoService.listarTodosProdutos());
    }
    @GetMapping("/buscar")
    public ResponseEntity<ProdutoDTO> buscarProdutosPorNome(@RequestParam String nome){
        return ResponseEntity.ok(produtoService.buscarProdutosPorNome(nome));
    }

    @PostMapping()
    public  ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produto = produtoService.cadastrarProduto(produtoDTO);
        return ResponseEntity.ok(produto);
    }

    @PutMapping()
    public ResponseEntity<ProdutoDTO> atualizarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.atualizarProduto(produtoDTO));
    }
    @DeleteMapping()
    public ResponseEntity<Void> deletarProduto(@RequestBody ProdutoDTO produtoDTO) {
        produtoService.deletarProduto(produtoDTO.getId());
        return ResponseEntity.noContent().build();
    }
}
