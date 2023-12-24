package com.transacoes.controllers;

import com.transacoes.clientes.Cliente;
import com.transacoes.dtos.ClientDTO;
import com.transacoes.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody ClientDTO cliente) {

        Cliente novoCliente = clientService.createClient(cliente);

        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        
        List<Cliente> clientes = this.clientService.getAllClientes();

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
}
