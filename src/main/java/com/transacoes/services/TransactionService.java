package com.transacoes.services;

import com.transacoes.clientes.Cliente;
import com.transacoes.dtos.TransactionDTO;
import com.transacoes.repositories.TransactionRepository;
import com.transacoes.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {

        Cliente sender = this.clientService.findClienteByid(transaction.senderId());
        Cliente receiver = this.clientService.findClienteByid(transaction.receiverId());

        System.out.println(sender);

        clientService.validateTransaction(sender, transaction.value());

        boolean autorizado = this.authorizeTransaction(sender, transaction.value());

        if(!autorizado)
        {
            throw new Exception("Transação não autorizada");
        }

        var newTransaction = new Transaction();

        newTransaction.setBalance(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.clientService.saveClient(sender);
        this.clientService.saveClient(receiver);

        return newTransaction;
    }

    public boolean authorizeTransaction(Cliente sender, BigDecimal value) {

        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if(!(authorizationResponse.getStatusCode() == HttpStatus.OK)
           && !(authorizationResponse.getBody().get("message") == "Autorizado") )
        {
            return false;
        } else return true;
    }
}
