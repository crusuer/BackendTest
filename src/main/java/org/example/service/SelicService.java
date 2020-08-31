package org.example.service;

import org.example.model.dto.SelicDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SelicService {
    public BigDecimal calcularSelicAcumulada(SelicDTO[] ultimosValores) {
        BigDecimal acumulado = BigDecimal.ZERO;
        for(SelicDTO valor : ultimosValores) {
            acumulado = acumulado.add(new BigDecimal(valor.getValor()));
        }
        return acumulado;
    }
}
