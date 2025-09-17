package com.desafioItau.DesafioItau.service;

import com.desafioItau.DesafioItau.dto.StatisticsResponse;
import com.desafioItau.DesafioItau.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class TransactionService {

    private final Queue<Transaction> transactions = new ConcurrentLinkedDeque<>();

    public void addNewTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }
    public void deleteAllTransactions(){
        this.transactions.clear();
    }
    public StatisticsResponse getStats(long interval){
        DoubleSummaryStatistics stats = this.transactions.stream().filter(
                transaction -> transaction.getDataHora().isAfter(OffsetDateTime.now().minusSeconds(interval)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
        return new StatisticsResponse(stats.getCount(),stats.getSum(),stats.getAverage(),stats.getMax(),stats.getMin());
    }
}
