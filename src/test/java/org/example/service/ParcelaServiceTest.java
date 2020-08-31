package org.example.service;

import org.example.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
class ParcelaServiceTest {
    @Autowired
    private ParcelaService parcelaService;

    @Test
    void deveCalcularValorParcelaSemJuros() {
        // DADO
        BigDecimal valorFinanciado = BigDecimal.valueOf(1000);
        int qtdeParcelas = 5;

        // QUANDO
        BigDecimal valorParcela = parcelaService.calcularVlParcela(valorFinanciado, qtdeParcelas, null);
        // ENTAO
        assertEquals(BigDecimal.valueOf(200), valorParcela);
    }

    @Test
    void deveCalcularValorParcelaComJuros() {
        // DADO
        BigDecimal valorFinanciado = BigDecimal.valueOf(1000);
        int qtdeParcelas = 10;
        Float taxaJurosMensal = 1.15F;

        // QUANDO
        BigDecimal valorParcela = parcelaService.calcularVlParcela(valorFinanciado, qtdeParcelas, taxaJurosMensal);
        // ENTAO
        assertAll(
                () -> assertTrue(valorParcela.compareTo(BigDecimal.valueOf(106)) > 0),
                () -> assertTrue(valorParcela.compareTo(BigDecimal.valueOf(107)) < 0)
        );
    }
}