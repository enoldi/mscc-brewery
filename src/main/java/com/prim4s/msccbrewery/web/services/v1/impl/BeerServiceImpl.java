package com.prim4s.msccbrewery.web.services.v1.impl;

import com.prim4s.msccbrewery.web.model.BeerDto;
import com.prim4s.msccbrewery.web.services.v1.BeerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by CHAMGOUE on 21/07/2020
 */

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("CASTEL")
                .beerStyle("BEER")
                .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        // todo to impl methode to update beer
        log.debug("Updating beerDto : {}", beerDto);
    }

    @Override
    public void deleteById(UUID beerId) {
        log.debug("Deleting beer .........................");
    }
}
