    package com.barbearia.controllers;

    import com.barbearia.dtos.ClienteDTO;
    import com.barbearia.models.Cliente;
    import com.barbearia.services.ClienteService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/clientes")
    public class ClienteController {
        @Autowired
        private ClienteService clienteService;

        @GetMapping(value = "/{id}")
        public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id){
            return ResponseEntity.ok(clienteService.getClienteById(id));
        }
        @GetMapping
        public ResponseEntity<List<ClienteDTO>> getAllCliente() {
            return ResponseEntity.ok(clienteService.getAllCliente());
        }
        @PostMapping
        public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
            return ResponseEntity.ok(clienteService.createCliente(clienteDTO));
        }
        @PutMapping()
        public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
            return ResponseEntity.ok(clienteService.updateCliente(clienteDTO));
        }
        @DeleteMapping(value = "/{id}")
        public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
            clienteService.deleteCliente(id);
            return ResponseEntity.noContent().build();
        }
    }
