//package com.barbearia.spec;
//
//import com.barbearia.exceptions.BussinesException;
//import com.barbearia.services.BarbeiroService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import static java.util.Objects.isNull;
//
//@Service
//public class BarbeiroSpec {
//    private static final String MSG_id = "Barbeiro não encontrado";
//
//    @Autowired
//    private BarbeiroService barbeiroService;
//
//    public void verificarCampoIdBarbeiroNulo(Long id){
//        if (isNull(id)){
//            throw new BussinesException(MSG_id);
//        }
//    }
//}
