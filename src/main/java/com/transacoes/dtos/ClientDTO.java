package com.transacoes.dtos;

import com.transacoes.clientes.ClientType;

import java.math.BigDecimal;

public record ClientDTO(String firstName, String lastName, String documento, BigDecimal balance, String email, ClientType clientType) {
}
