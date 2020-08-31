package org.example.service;

import org.example.Application;
import org.example.exception.ValorEntradaIncorretoException;
import org.example.model.dto.CompraDTO;
import org.example.model.entity.CondicaoPagamento;
import org.example.model.entity.Parcela;
import org.example.model.entity.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
class CompraServiceTest {
    private final Produto produto = new Produto(123L, "Lavadora XPTO", BigDecimal.valueOf(1000));
    @Autowired
    private CompraService compraService;

    @Test
    void deveGerarCompraComParcelaUnica() {
        // DADO
        CondicaoPagamento condicaoPagamento = new CondicaoPagamento(BigDecimal.valueOf(900), 1);
        CompraDTO compraDTO = new CompraDTO(produto, condicaoPagamento);

        // QUANDO
        final Parcela[] parcelas = compraService.geraParcelas(compraDTO);

        // ENTAO
        assertAll(
                () -> assertEquals(1, parcelas.length),
                () -> assertEquals(BigDecimal.valueOf(10000, 2), parcelas[0].getValor()),
                () -> assertNull(parcelas[0].getTaxaJurosAoMes())
        );
    }

    @Test
    void deveGerarCompraSemJuros() {
        // DADO
        CondicaoPagamento condicaoPagamento = new CondicaoPagamento(BigDecimal.valueOf(400), 6);
        CompraDTO compraDTO = new CompraDTO(produto, condicaoPagamento);

        // QUANDO
        final Parcela[] parcelas = compraService.geraParcelas(compraDTO);

        // ENTAO
        assertAll(
                () -> assertEquals(6, parcelas.length),
                () -> assertEquals(BigDecimal.valueOf(10000, 2), parcelas[0].getValor()),
                () -> assertNull(parcelas[0].getTaxaJurosAoMes())
        );
    }

    @Test
    void deveGerarCompraComJuros() {
        // DADO
        CondicaoPagamento condicaoPagamento = new CondicaoPagamento(BigDecimal.ZERO, 10);
        CompraDTO compraDTO = new CompraDTO(produto, condicaoPagamento);

        // QUANDO
        final Parcela[] parcelas = compraService.geraParcelas(compraDTO);

        // ENTAO
        assertAll(
                () -> assertEquals(10, parcelas.length),
                () -> assertEquals(BigDecimal.valueOf(106.43), parcelas[0].getValor()),
                () -> assertEquals(1.15F, parcelas[0].getTaxaJurosAoMes())
        );
    }

    @Test
    void deveGerarExcecaoParaValorEntradaMaiorQueValorCompra() {
        // DADO
        CondicaoPagamento condicaoPagamento = new CondicaoPagamento(BigDecimal.valueOf(5000), 10);
        CompraDTO compraDTO = new CompraDTO(produto, condicaoPagamento);

        // QUANDO
        assertThrows(ValorEntradaIncorretoException.class, () -> compraService.geraParcelas(compraDTO));
    }
}
