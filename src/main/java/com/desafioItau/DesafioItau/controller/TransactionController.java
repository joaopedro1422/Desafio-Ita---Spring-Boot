package com.desafioItau.DesafioItau.controller;

import com.desafioItau.DesafioItau.dto.TransactionRequestDto;
import com.desafioItau.DesafioItau.model.Transaction;
import com.desafioItau.DesafioItau.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }
    @PostMapping
    public ResponseEntity<Void> createNewTransaction(@RequestBody @Valid TransactionRequestDto transactionData){
        if(transactionData.dataHora().isAfter(OffsetDateTime.now()) || transactionData.valor() < 0 ){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        transactionService.addNewTransaction(new Transaction(transactionData.valor(), transactionData.dataHora()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTransactions(){
        transactionService.deleteAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
