package com.barbearia.spec;

import com.barbearia.dtos.ClienteDTO;
import com.barbearia.exceptions.BusinesException;
import com.barbearia.models.Cliente;
import com.barbearia.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ClienteSpec {
    private static final String MSG_EMAIL = "Usuário já cadastrado com email: %s.";
    private static final String MSG_ID = "Id não pode ser nulo.";

    @Autowired
    private ClienteRepository clienteRepository;

    public void verificarSeExisteClienteComEmailDuplicado(Cliente cliente) {
        if (nonNull(cliente)) {
            throw new BusinesException(
                    String.format(MSG_EMAIL, cliente.getEmail()));
        }
    }
    public void verificarCampoIdNulo(Long id) {
        if (isNull(id)) throw new BusinesException(MSG_ID);
    }

    public void verificarEmailEmUso(Cliente cliente, ClienteDTO clienteDTO) {
        boolean alterouEmail = !(cliente.getEmail().equals(clienteDTO.getEmail()));

        if (alterouEmail) {
            boolean encontrouUsuarioComEmailInformado =
                    nonNull(clienteRepository
                            .findByEmail(clienteDTO.getEmail()));

            if(encontrouUsuarioComEmailInformado)
                throw new BusinesException(String.format(MSG_EMAIL,
                        clienteDTO.getEmail()));
        }
    }
}
