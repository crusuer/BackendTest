package org.example.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.example.model.entity.Parcela;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ParcelaDTO {
    private final Parcela[] parcelas;
}
