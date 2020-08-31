package org.example.rest.compra;

import org.example.model.dto.CompraDTO;
import org.example.model.entity.Parcela;
import org.example.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompraController implements CompraEndpoint {
    @Autowired
    private CompraService compraService;

    @Override
    public Parcela[] geraParcelas(@RequestBody CompraDTO compraDTO) {
        return compraService.geraParcelas(compraDTO);
    }
}
