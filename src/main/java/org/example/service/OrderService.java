package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.CompraDTO;
import org.example.model.entity.CondicaoPagamento;
import org.example.model.entity.Parcela;
import org.example.model.entity.Produto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class OrderService {

    public Parcela[] geraParcelas(CompraDTO compraDTO) {
        CondicaoPagamento condicaoPagamento = compraDTO.getCondicaoPagamento();
        Produto produto = compraDTO.getProduto();
        BigDecimal valorFinaciado = condicaoPagamento.calcularValorFinanciado();

        int qtdeParcelas = condicaoPagamento.getQtdeParcelas();
        Parcela[] parcelas = new Parcela[qtdeParcelas];

        for (int i = 0; i < parcelas.length; i++) {
            parcelas[i] = new Parcela(1 + i, produto.getValor(), 1.15F);
        }

        return parcelas;
    }
}
