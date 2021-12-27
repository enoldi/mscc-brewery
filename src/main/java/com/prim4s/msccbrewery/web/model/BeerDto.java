package com.prim4s.msccbrewery.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

/**
 * Chamgoue created on 21/07/2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

    @Null
    private UUID id;

    @NotBlank
    private String beerName;
    
    @NotBlank
    private String beerStyle;

    @Positive
    private Long upc;
}
