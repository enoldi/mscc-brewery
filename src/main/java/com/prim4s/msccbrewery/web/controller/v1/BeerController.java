package com.prim4s.msccbrewery.web.controller.v1;

import com.prim4s.msccbrewery.web.model.BeerDto;
import com.prim4s.msccbrewery.web.services.BeerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import javax.validation.Valid;

/**
 * Chamgoue created on 21/07/2020
 */

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    /**
     * Get beer by id
     * @param beerId
     * @return
     */
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerDto(@PathVariable("beerId") UUID beerId) {

        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    /**
     * Post - create new beer
     * @param beerDto
     * @return
     */
    @PostMapping
    public ResponseEntity postBeerDto(@Valid @RequestBody BeerDto beerDto) {

        BeerDto beerDtoSaved = beerService.saveNewBeer(beerDto);

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
        beerService.updateBeer(beerId, beerDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * delete beer by id
     * @param beerId
     */
    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
    }
}
