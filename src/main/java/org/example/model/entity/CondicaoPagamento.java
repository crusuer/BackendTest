package org.example.model.entity;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
public class CondicaoPagamento {
    @DecimalMin(value = "0.0", inclusive = false)
    private final BigDecimal valorEntrada;
    @Min(0)
    private final int qtdeParcelas;
}
