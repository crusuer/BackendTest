package org.example.service;

import org.example.Application;
import org.example.model.dto.SelicDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class)
class SelicServiceTest {
    @Autowired
    private SelicService selicService;

    @Test
    void deveRetornarValorAcumuladoSelic() {
        // DADO
        SelicDTO[] ultimosValores = new SelicDTO[3];
        ultimosValores[0] = new SelicDTO("01/01/2020", "0.002");
        ultimosValores[1] = new SelicDTO("02/01/2020", "0.002");
        ultimosValores[2] = new SelicDTO("03/01/2020", "0.002");
        // QUANDO
        BigDecimal acumulado = selicService.calcularSelicAcumulada(ultimosValores);
        // ENTAO
        assertEquals(BigDecimal.valueOf(0.006), acumulado);
    }
}