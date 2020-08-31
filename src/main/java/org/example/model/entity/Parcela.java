package org.example.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Parcela {
    private final int numeroParcela;
    private final BigDecimal valor;
    private final Float taxaJurosAoMes;
}
