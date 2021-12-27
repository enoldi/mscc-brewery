package com.prim4s.msccbrewery.web.controller.v2;

import com.prim4s.msccbrewery.web.model.BeerDto;
import com.prim4s.msccbrewery.web.services.v2.BeerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Chamgoue created on 21/07/2020
 */

@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

    private final BeerService beerServiceV2;

    public BeerControllerV2(BeerService beerService) {
        this.beerServiceV2 = beerService;
    }

    /**
     * Get beer by id
     * @param beerId
     * @return
     */
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerDto(@PathVariable("beerId") UUID beerId) {

        return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
    }

    /**
     * Post - create new beer
     * @param beerDto
     * @return
     */
    @PostMapping
    public ResponseEntity postBeerDto(@Valid @RequestBody BeerDto beerDto) {

        BeerDto beerDtoSaved = beerServiceV2.saveNewBeer(beerDto);

        HttpHeaders headers = new HttpHeaders();

        // todo add hostname url
        headers.add("Location", "/api/v1/beer/" + beerDtoSaved.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    /**
     * update beer
     * @param beerId
     * @param beerDto
     * @return
     */
    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {
        beerServiceV2.updateBeer(beerId, beerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * delete beer by id
     * @param beerId
     */
    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        beerServiceV2.deleteById(beerId);
    }
}
