package org.example.model.entity;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class Produto {
    private final long codigo;
    private final String nome;
    private final BigDecimal valor;

    public boolean validaProduto() {
        return nome != null && !nome.isEmpty()
                && valor != null && valor.compareTo(BigDecimal.ZERO) > 0;
    }
}
