package com.prim4s.msccbrewery.web.services.v2;

import com.prim4s.msccbrewery.web.model.BeerDto;

import java.util.UUID;

/**
 * Created by CHAMGOUE on 21/07/2020
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    void updateBeer(UUID beerId, BeerDto beerDto);

    void deleteById(UUID beerId);
}
