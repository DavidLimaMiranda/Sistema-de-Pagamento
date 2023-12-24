package com.transacoes.repositories;

import com.transacoes.clientes.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Cliente, Long> {
    
    Optional<Cliente> findClientById(Long id);
}
