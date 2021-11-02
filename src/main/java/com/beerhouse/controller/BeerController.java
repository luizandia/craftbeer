package com.beerhouse.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import com.beerhouse.entity.Beer;
import com.beerhouse.service.BeerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/beers")
@Validated
public class BeerController {

    @Autowired
	private BeerService beerService;

    @GetMapping()
	public ResponseEntity<List<Beer>> getBeers() {
        return ResponseEntity.ok(beerService.getBeers());
    }

    @GetMapping("/{id}")
	public ResponseEntity<Beer> getBeer(@PathVariable @Positive Integer id) {
        return ResponseEntity.ok(beerService.getBeer(id));
	}

    @PostMapping()
	public ResponseEntity<Void> addBeer(@Valid @RequestBody Beer beer) {
        beer = beerService.insert(beer);
        URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(beer.getId())
                    .toUri();
        return ResponseEntity.created(location).build();
	}

    @PutMapping("/{id}")
    public ResponseEntity<Beer> updateBeer(
        @Valid @RequestBody Beer beer, 
        @PathVariable("id") @Positive Integer id) {

        beer.setId(id);
        beerService.update(beer);  
        return ResponseEntity.ok(beer);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Beer> partialUpdateBeer(
        @RequestBody Beer partialBeer, 
        @PathVariable("id") @Positive Integer id) {
    
        partialBeer.setId(id);
        return ResponseEntity.ok(beerService.partialUpdate(partialBeer));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable @Positive Integer id) {
      beerService.delete(id);
    }
}
