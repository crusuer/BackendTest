package org.example.model.entity;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class Produto {
    @Min(0)
    private final long codigo;
    @NotNull
    @NotBlank
    private final String nome;
    @DecimalMin(value = "0.0", inclusive = false)
    private final BigDecimal valor;
}
