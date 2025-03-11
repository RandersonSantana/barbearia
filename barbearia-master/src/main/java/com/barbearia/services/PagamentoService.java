//package com.barbearia.services;
//
//import com.barbearia.dtos.PagamentoDTO;
//import com.barbearia.models.Pagamento;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PagamentoService {
//
//    @Autowired
//    private PagamentoRepository pagamentoRepository;
//
//    public PagamentoDTO converterPagamentoParaPagamentoDTO(Pagamento pagamento){
//        PagamentoDTO pagamentoDTO = new PagamentoDTO(pagamento.getId(),
//                pagamento.getValor(),
//                pagamento.getMetodo(),
//                pagamento.getStatus());
//        return pagamentoDTO;
//    }
//}
