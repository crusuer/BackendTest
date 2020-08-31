package org.example.rest.selic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.rest.EndpointUrls;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Api(value = "abc", tags = {"API"})
public interface SelicEndpoint {
    @GetMapping(path = EndpointUrls.SELIC, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Retorna Selic acumulada dos últimos 30 dias")
    BigDecimal consultaSelicAcumulada();
}
