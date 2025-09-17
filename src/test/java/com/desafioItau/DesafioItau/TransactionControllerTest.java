package com.desafioItau.DesafioItau;

import com.desafioItau.DesafioItau.controller.TransactionController;
import com.desafioItau.DesafioItau.dto.TransactionRequestDto;
import com.desafioItau.DesafioItau.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private TransactionService transactionService;
    @BeforeEach
    void setUp() {
        transactionService = Mockito.mock(TransactionService.class);
    }
    @Test
    void creatNotValidTransaction() throws Exception{
        var request = new TransactionRequestDto(129.90, OffsetDateTime.now().minusSeconds(60));
        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated()); // 201
    }
    @Test
    void createNotValidNegativeTransaction() throws Exception{
        var request = new TransactionRequestDto(-129.90, OffsetDateTime.now().minusSeconds(60));
        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnprocessableEntity()); // 422
    }

    @Test
    void createNotValidFutureTransaction() throws Exception{
        var request = new TransactionRequestDto(100.0, OffsetDateTime.now().plusSeconds(60));
        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnprocessableEntity()); // 422
    }


}
