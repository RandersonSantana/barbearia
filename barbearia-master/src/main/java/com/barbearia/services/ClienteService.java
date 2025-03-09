package com.barbearia.services;

import com.barbearia.dtos.ClienteDTO;
import com.barbearia.exceptions.BusinesException;
import com.barbearia.models.Cliente;
import com.barbearia.repositories.ClienteRepository;
import com.barbearia.spec.ClienteSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private static final String MSG_CLIENTE = "Cliente não encontrado";

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteSpec clienteSpec;

    @Transactional(readOnly = true)
        public ClienteDTO getClienteById(Long id){

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinesException(MSG_CLIENTE));

        return converterClienteParaClienteDTO(cliente);
    }

    public ClienteDTO converterClienteParaClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setTelefone(cliente.getTelefone());
        clienteDTO.setEmail(cliente.getEmail());
        return clienteDTO;
    }

    public Cliente converterClienteDTOParaCliente(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEmail(clienteDTO.getEmail());
        return cliente;
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> getAllCliente(){
    List<Cliente> result = clienteRepository.findAll();
    List<ClienteDTO> clienteDTO = new ArrayList<>();
    for (Cliente cliente : result){
        clienteDTO.add(new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail()));
    }
    return clienteDTO;
    }

    @Transactional
    public ClienteDTO createCliente(ClienteDTO clienteDTO){

        clienteDTO.setId(null);

        Cliente ClienteEmail = clienteRepository.findByEmail(clienteDTO.getEmail());
        clienteSpec.verificarSeExisteClienteComEmailDuplicado(ClienteEmail);

        Cliente cliente = converterClienteDTOParaCliente(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return converterClienteParaClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO updateCliente(ClienteDTO clienteDTO){
        Cliente cliente = clienteRepository.findById(clienteDTO.getId())
                .orElseThrow(() -> new BusinesException(MSG_CLIENTE));

        clienteSpec.verificarEmailEmUso(cliente, clienteDTO);
        clienteSpec.verificarCampoIdNulo(clienteDTO.getId());

        cliente= converterClienteDTOParaCliente(clienteDTO);
        clienteRepository.save(cliente);
        return converterClienteParaClienteDTO(cliente);
    }

    public void deleteCliente(Long id){
        clienteRepository.deleteById(id);
    }
}
