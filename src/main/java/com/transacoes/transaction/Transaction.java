package com.transacoes.transaction;

import com.transacoes.clientes.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Cliente sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Cliente receiver;

    private LocalDateTime timestamp;
}
