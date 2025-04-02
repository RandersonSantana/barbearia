package com.barbearia.services;

import com.barbearia.dtos.ProdutoDTO;
import com.barbearia.exceptions.BusinesException;
import com.barbearia.models.Produto;
import com.barbearia.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProdutoService {
    private static final String MSG_PRODUTO = "Produto não encontrado";

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoDTO converterProdutoParaProdutoDTO(Produto produto){
        ProdutoDTO produtoDTO = new ProdutoDTO(produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidadeEmEstoque());
        return produtoDTO;
    }
    public Produto converterProdutoDTOParaProduto(ProdutoDTO produtoDTO){
        Produto produto = new Produto(produtoDTO.getId(),
                produtoDTO.getNome(),
                produtoDTO.getPreco(),
                produtoDTO.getQuantidadeEmEstoque());
        return produto;
    }
    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO){
        Produto produto = converterProdutoDTOParaProduto(produtoDTO);
        produto = produtoRepository.save(produto);
        return converterProdutoParaProdutoDTO(produto);
    }

    public ProdutoDTO atualizarProduto(ProdutoDTO produtoDTO){
        produtoRepository.findById(produtoDTO.getId())
                .orElseThrow(() -> new BusinesException(MSG_PRODUTO));

        Produto produto = converterProdutoDTOParaProduto(produtoDTO);
        produtoRepository.save(produto);
        return converterProdutoParaProdutoDTO(produto);
    }

    public List<ProdutoDTO> listarTodosProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDTO> produtoDTOs = new ArrayList<>();
        for (Produto produto : produtos) {
            produtoDTOs.add(converterProdutoParaProdutoDTO(produto));
        }
        return produtoDTOs;
    }

    public ProdutoDTO buscarProdutoPorId(Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new BusinesException(MSG_PRODUTO));
        return converterProdutoParaProdutoDTO(produto);
    }
    public ProdutoDTO buscarProdutosPorNome(String nome){
        Produto produto = produtoRepository.findByNome(nome)
                .orElseThrow(() -> new BusinesException(MSG_PRODUTO));
        return converterProdutoParaProdutoDTO(produto);
    }

    public  void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }
}
