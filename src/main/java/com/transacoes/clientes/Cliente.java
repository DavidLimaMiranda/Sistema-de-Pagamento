package com.transacoes.clientes;

import com.transacoes.dtos.ClientDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "clientes")
@Table(name = "Clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private ClientType typeClient;

    public Cliente(ClientDTO novoCliente) {

        this.firstName = novoCliente.firstName();
        this.lastName = novoCliente.lastName();
        this.balance = novoCliente.balance();
        this.document = novoCliente.documento();
        this.typeClient = novoCliente.clientType();
        this.email = novoCliente.email();
    }

}
