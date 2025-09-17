package com.desafioItau.DesafioItau.dto;

public record StatisticsResponse(
        long count,
        double sum,
        double avg,
        double max,
        double min
) {
}
