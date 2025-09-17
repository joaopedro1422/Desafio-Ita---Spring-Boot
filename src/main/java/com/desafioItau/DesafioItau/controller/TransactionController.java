package com.desafioItau.DesafioItau.controller;

import com.desafioItau.DesafioItau.dto.TransactionRequestDto;
import com.desafioItau.DesafioItau.model.Transaction;
import com.desafioItau.DesafioItau.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }
    @PostMapping
    public ResponseEntity<?> createNewTransaction(@RequestBody @Valid TransactionRequestDto transactionData){
        if(transactionData.dataHora().isAfter(OffsetDateTime.now())){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        transactionService.addNewTransaction(new Transaction(transactionData.valor(), transactionData.dataHora()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
