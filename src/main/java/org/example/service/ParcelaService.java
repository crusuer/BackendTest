package org.example.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ParcelaService {
    private BigDecimal calcularValorParcelaComJuros(BigDecimal valorFinanciado, int qtdeParcelas, float taxaJurosMensal) {
        float taxaJurosUnitaria = taxaJurosMensal / 100;
        BigDecimal jurosTotal = BigDecimal.valueOf(1 + taxaJurosUnitaria).pow(qtdeParcelas);

        return valorFinanciado.multiply(
                jurosTotal.multiply(BigDecimal.valueOf(taxaJurosUnitaria))
                        .divide(jurosTotal.subtract(BigDecimal.ONE), RoundingMode.HALF_UP)
        );
    }

    private BigDecimal calcularValorParcelaSemJuros(BigDecimal valorFinanciado, int qtdeParcelas) {
        return valorFinanciado.divide(BigDecimal.valueOf(qtdeParcelas), RoundingMode.HALF_UP);
    }

    public BigDecimal calcularValorParcela(BigDecimal valorFinanciado, int qtdeParcelas, Float taxaJurosMensal) {
        return taxaJurosMensal == null ? calcularValorParcelaSemJuros(valorFinanciado, qtdeParcelas)
                : calcularValorParcelaComJuros(valorFinanciado, qtdeParcelas, taxaJurosMensal);
    }
}
