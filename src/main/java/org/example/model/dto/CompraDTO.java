package org.example.model.dto;

import lombok.Data;
import org.example.model.entity.CondicaoPagamento;
import org.example.model.entity.Produto;

@Data
public class CompraDTO {
    private final Produto produto;
    private final CondicaoPagamento condicaoPagamento;
}
