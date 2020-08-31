package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.ValorEntradaIncorretoException;
import org.example.model.dto.CompraDTO;
import org.example.model.entity.CondicaoPagamento;
import org.example.model.entity.Parcela;
import org.example.model.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Slf4j
public class CompraService {
    @Autowired
    private ParcelaService parcelaService;

    public Parcela[] geraParcelas(CompraDTO compraDTO) {
        CondicaoPagamento condicaoPagamento = compraDTO.getCondicaoPagamento();
        Produto produto = compraDTO.getProduto();
        BigDecimal valorFinanciado = calcularVlFinanciado(condicaoPagamento.getValorEntrada(), produto.getValor());

        return preencherParcelas(valorFinanciado, condicaoPagamento.getQtdeParcelas());
    }

    private BigDecimal calcularVlFinanciado(BigDecimal valorEntrada, BigDecimal valorCompra) {
        if (valorEntrada.compareTo(valorCompra) > 0) {
            throw new ValorEntradaIncorretoException("Valor de entrada é maior que o valor total da compra.");
        }
        return valorCompra.subtract(valorEntrada);
    }

    private Parcela[] preencherParcelas(BigDecimal valorFinanciado, int qtdeParcelas) {
        Parcela[] parcelas = new Parcela[qtdeParcelas];
        Float taxaJurosMensal = null;
        if (qtdeParcelas > 6) {
            taxaJurosMensal = 1.15F;
        }
        BigDecimal valorParcela = parcelaService.calcularValorParcela(valorFinanciado, qtdeParcelas, taxaJurosMensal);

        for (int i = 0; i < parcelas.length; i++) {
            parcelas[i] = new Parcela(1 + i,
                    valorParcela.setScale(2, RoundingMode.HALF_UP), taxaJurosMensal);
        }

        return parcelas;
    }
}
