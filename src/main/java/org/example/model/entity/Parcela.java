package org.example.model.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Parcela {
    private final int numeroParcela;
    private final BigDecimal valor;
    private final Float taxaJurosAoMes;
}
