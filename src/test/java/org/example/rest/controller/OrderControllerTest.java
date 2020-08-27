package org.example.rest.controller;

import com.google.gson.Gson;
import org.example.Application;
import org.example.model.dto.CompraDTO;
import org.example.model.entity.CondicaoPagamento;
import org.example.model.entity.Produto;
import org.example.rest.EndpointUrls;
import org.example.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void deveGerarParcelas() throws Exception {
        // DADO
        CondicaoPagamento condicaoPagamento = new CondicaoPagamento(
                BigDecimal.valueOf(1000), 1);
        Produto produto = new Produto(123L, "Cama Box", BigDecimal.valueOf(2000));
        CompraDTO compraDTO = new CompraDTO(produto, condicaoPagamento);

        // QUANDO
        this.mockMvc.perform(
                put(EndpointUrls.PARCELAS)
                        .content(new Gson().toJson(compraDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                // ENTAO
                .andDo(print())
                .andExpect(status().isOk());

    }
}