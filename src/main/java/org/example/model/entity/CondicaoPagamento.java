package org.example.model.entity;

import lombok.Data;
import org.example.exception.ValorEntradaIncorretoException;

import java.math.BigDecimal;

@Data
public class CondicaoPagamento {
    private final BigDecimal valorEntrada;
    private final int qtdeParcelas;

    public BigDecimal calcularValorFinanciado(BigDecimal valorCompra) {
        if(valorCompra.compareTo(valorEntrada) < 0) {
            throw new ValorEntradaIncorretoException("Valor de entrada é maior que o valor total da compra.", null);
        }
        return valorCompra.subtract(valorEntrada);
    }
}
