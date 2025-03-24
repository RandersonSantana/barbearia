package com.barbearia.services;

import com.barbearia.dtos.PagamentoDTO;
import com.barbearia.exceptions.BusinesException;
import com.barbearia.models.Pagamento;
import com.barbearia.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagamentoService {
    private static final String MSG_CLIENTE = "Pagamento não encontrado";

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public PagamentoDTO buscarPagamentoPorId(Long id){
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new BusinesException(MSG_CLIENTE));
        return converterPagamentoParaPagamentoDTO(pagamento);
    }

    public PagamentoDTO converterPagamentoParaPagamentoDTO(Pagamento pagamento) {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setId(pagamento.getId());
        pagamentoDTO.setValor(pagamento.getValor());
        pagamentoDTO.setMetodo(pagamento.getMetodo());
        pagamentoDTO.setStatus(pagamento.getStatus());
        return pagamentoDTO;
    }

    public Pagamento converterPagamentoDTOParaPagamento(PagamentoDTO pagamentoDTO){
        Pagamento pagamento = new Pagamento();
        pagamento.setId(pagamentoDTO.getId());
        pagamento.setValor(pagamentoDTO.getValor());
        pagamento.setMetodo(pagamentoDTO.getMetodo());
        pagamento.setStatus(pagamentoDTO.getStatus());
        return pagamento;
    }
    public PagamentoDTO cadastrarPagamento(PagamentoDTO pagamentoDTO){
        Pagamento pagamento = converterPagamentoDTOParaPagamento(pagamentoDTO);
        pagamento = pagamentoRepository.save(pagamento);
        return converterPagamentoParaPagamentoDTO(pagamento);
    }

    public PagamentoDTO atualizarPagamento(PagamentoDTO pagamentoDTO){
        Pagamento pagamento = pagamentoRepository.findById(pagamentoDTO.getId())
                .orElseThrow(() -> new BusinesException(MSG_CLIENTE));

        pagamento= converterPagamentoDTOParaPagamento(pagamentoDTO);
        pagamentoRepository.save(pagamento);
        return converterPagamentoParaPagamentoDTO(pagamento);
    }
    public void deletarPagamento(Long id){
        pagamentoRepository.deleteById(id);
    }
}
