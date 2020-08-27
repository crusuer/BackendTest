package org.example.rest.controller;

import org.example.model.dto.CompraDTO;
import org.example.model.entity.Parcela;
import org.example.rest.EndpointUrls;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PutMapping(path = EndpointUrls.PARCELAS)
    public Parcela[] geraParcelas(@RequestBody CompraDTO compraDTO) {
        return orderService.geraParcelas(compraDTO);
    }
}
