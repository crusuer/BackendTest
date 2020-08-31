package org.example.model.entity;

import lombok.Data;

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
    @NotNull
    private final BigDecimal valor;

    public boolean validarProduto() {
        return valor.compareTo(BigDecimal.ZERO) > 0;
    }
}
