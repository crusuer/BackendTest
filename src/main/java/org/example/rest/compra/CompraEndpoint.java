package org.example.rest.compra;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.model.dto.CompraDTO;
import org.example.model.entity.Parcela;
import org.example.rest.EndpointUrls;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = {"Compra"})
public interface CompraEndpoint {
    @PostMapping(path = EndpointUrls.COMPRA, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Simula as parcelas geradas por uma compra", tags = {"Compra"})
    Parcela[] geraParcelas(@RequestBody CompraDTO compraDTO);
}
