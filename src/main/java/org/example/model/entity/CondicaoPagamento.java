package org.example.model.entity;

import lombok.Data;
import org.example.exception.ValorEntradaIncorretoException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CondicaoPagamento {
    @NotNull
    private final BigDecimal valorEntrada;
    @Min(0)
    private final int qtdeParcelas;

    public BigDecimal calcularValorFinanciado(BigDecimal valorCompra) {
        if(valorCompra.compareTo(valorEntrada) < 0) {
            throw new ValorEntradaIncorretoException("Valor de entrada é maior que o valor total da compra.");
        }
        return valorCompra.subtract(valorEntrada);
    }
}
