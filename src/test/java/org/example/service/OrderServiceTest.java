package org.example.service;

import org.example.Application;
import org.example.model.dto.CompraDTO;
import org.example.model.entity.CondicaoPagamento;
import org.example.model.entity.Parcela;
import org.example.model.entity.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
@Rollback
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    private CondicaoPagamento condicaoPagamento;
    private Produto produto;

    @Test
    void deveCriarParcelaUnica() {
        // DADO
        condicaoPagamento = new CondicaoPagamento(BigDecimal.valueOf(500), 1);
        produto = new Produto(123L, "Lavadora", BigDecimal.valueOf(600));
        CompraDTO compraDTO = new CompraDTO(produto, condicaoPagamento);

        // QUANDO
        final Parcela[] parcelas = orderService.geraParcelas(compraDTO);

        // ENTAO
        assertAll(
                () -> assertEquals(1, parcelas.length)
        );
    }
}
