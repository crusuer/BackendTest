package org.example.rest.selic;

import org.example.model.dto.SelicDTO;
import org.example.service.SelicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
public class SelicController implements SelicEndpoint {
    private static final String URL =
            "https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/dados/ultimos/30?formato=json";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SelicService selicService;

    @Override
    public BigDecimal consultaSelicAcumulada() {
        ResponseEntity<SelicDTO[]> response = restTemplate.getForEntity(URL, SelicDTO[].class);
        return selicService.calcularSelicAcumulada(response.getBody());
    }
}
