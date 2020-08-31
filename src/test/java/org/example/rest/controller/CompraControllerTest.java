package org.example.rest.controller;

import com.google.gson.Gson;
import org.example.Application;
import org.example.model.dto.CompraDTO;
import org.example.model.dto.ParcelaDTO;
import org.example.model.entity.CondicaoPagamento;
import org.example.model.entity.Parcela;
import org.example.model.entity.Produto;
import org.example.rest.EndpointUrls;
import org.example.service.CompraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class CompraControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompraService compraService;

    @Test
    void deveGerarParcelasSemJuros() throws Exception {
        // DADO
        CondicaoPagamento condicaoPagamento = new CondicaoPagamento(
                BigDecimal.valueOf(1000), 1);
        Produto produto = new Produto(123L, "Cama Box", BigDecimal.valueOf(2000));
        CompraDTO compraDTO = new CompraDTO(produto, condicaoPagamento);

        Parcela[] parcelas = new Parcela[1];
        parcelas[0] = new Parcela(1, BigDecimal.valueOf(1000), null);
        when(compraService.geraParcelas(compraDTO)).thenReturn(parcelas);

        // QUANDO
        this.mockMvc.perform(
                put(EndpointUrls.PARCELAS)
                        .content(new Gson().toJson(compraDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                // ENTAO
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{ \"numeroParcela\": 1, \"valor\": 1000.00, \"taxaJurosAoMes\": null }]"));
    }

    @Test
    void deveGerarParcelasComJuros() throws Exception {
        // DADO
        CondicaoPagamento condicaoPagamento = new CondicaoPagamento(
                BigDecimal.valueOf(1000), 8);
        Produto produto = new Produto(123L, "Cama Box", BigDecimal.valueOf(2000));
        CompraDTO compraDTO = new CompraDTO(produto, condicaoPagamento);

        Parcela[] parcelas = new Parcela[1];
        parcelas[0] = new Parcela(1, BigDecimal.valueOf(12500,2), 1.15F);
        when(compraService.geraParcelas(compraDTO)).thenReturn(parcelas);

        // QUANDO
        this.mockMvc.perform(
                put(EndpointUrls.PARCELAS)
                        .content(new Gson().toJson(compraDTO))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                // ENTAO
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{ \"numeroParcela\": 1, \"valor\": 125.00, \"taxaJurosAoMes\": 1.15 }]"));
    }
}