package com.transacoes.services;

import com.transacoes.clientes.Cliente;
import com.transacoes.clientes.ClientType;
import com.transacoes.dtos.ClientDTO;
import com.transacoes.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public void validateTransaction(Cliente sender, BigDecimal value) throws Exception {

        if(sender.getTypeClient() == ClientType.MERCHANT)
        {
            throw new Exception("Clientes do tipo Lojistas não podem realizar transações.");
        }

        if(sender.getBalance().compareTo(value) < 0)
        {
            throw new Exception("Você não possui essa quantia em seu saldo no momento para realizar essa transação.");
        }
    }

    public Cliente findClienteByid(Long id) throws Exception {

        return this.repository.findClientById(id).orElseThrow(() -> new Exception("Cliente não encontrado."));
    }

    public void saveClient(Cliente cliente) {

        this.repository.save(cliente);
    }

    public Cliente createClient(ClientDTO cliente) {
        var newClient = new Cliente(cliente);
        this.saveClient(newClient);

        return newClient;
    }

    public List<Cliente> getAllClientes() {

        return this.repository.findAll();
    }
}
