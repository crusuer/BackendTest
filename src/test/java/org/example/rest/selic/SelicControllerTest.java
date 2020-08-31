package org.example.rest.selic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Application;
import org.example.rest.EndpointUrls;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class SelicControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void deveRetornarValorAcumulado30DiasTaxaSelic() throws Exception {
        // QUANDO
        MvcResult result = this.mockMvc.perform(
                get(EndpointUrls.SELIC)
        )
                // ENTAO
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        BigDecimal acumulado = mapper.readValue(result.getResponse().getContentAsString(), BigDecimal.class);
        assertTrue(acumulado.compareTo(BigDecimal.ZERO) > 0);
    }
}