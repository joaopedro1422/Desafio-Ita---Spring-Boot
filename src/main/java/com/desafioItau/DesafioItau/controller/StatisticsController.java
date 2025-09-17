package com.desafioItau.DesafioItau.controller;

import com.desafioItau.DesafioItau.dto.StatisticsResponse;
import com.desafioItau.DesafioItau.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estatistica")
public class StatisticsController {

    private final TransactionService transactionService;
    public StatisticsController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<StatisticsResponse> getStats(@RequestParam long interval){
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getStats(interval));
    }
}
