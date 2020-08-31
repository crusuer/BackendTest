package org.example.rest.controller;

import org.example.model.dto.CompraDTO;
import org.example.model.entity.Parcela;
import org.example.rest.EndpointUrls;
import org.example.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompraController {
    @Autowired
    private CompraService compraService;

    @PostMapping(path = EndpointUrls.COMPRA)
    public Parcela[] geraParcelas(@RequestBody CompraDTO compraDTO) {
        return compraService.geraParcelas(compraDTO);
    }
}
