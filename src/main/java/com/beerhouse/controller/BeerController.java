package com.beerhouse.controller;

import java.net.URI;
import java.util.ArrayList;

import com.beerhouse.model.Beer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class BeerController {

    @GetMapping("/beers")
	public ResponseEntity<ArrayList<Beer>> getBeers() {
        ArrayList<Beer> beers = new ArrayList<>();
        beers.add(new Beer(1, "name", "ingredients", "alcoholContent", (float) 20.36, "category"));
        return ResponseEntity.ok(beers);
    }

    @GetMapping("/beers/{id}")
	public ResponseEntity<Beer> getBeer(@PathVariable Integer id) {
		return ResponseEntity.ok(new Beer(id, "name", "ingredients", "alcoholContent", (float) 20.36, "category"));
	}

    @PostMapping("/beers")
	public ResponseEntity<Void> addBeer(@RequestBody Beer beer) {
        URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(beer.getId())
                    .toUri();
        return ResponseEntity.created(location).build();
	}

    @PutMapping("/beers/{id}")
    public ResponseEntity<Beer> saveResource(
        @RequestBody Beer beer, 
        @PathVariable("id") Integer id) {
        
        beer.setId(id);    
        return ResponseEntity.ok(beer);
    }

    @PatchMapping("/beers/{id}")
    public ResponseEntity<Beer> partialUpdateName(
        @RequestBody Beer partialBeer, 
        @PathVariable("id") Integer id) {
    
        //partialBeer
        
        return ResponseEntity.ok(new Beer(id, "name", "ingredients", "alcoholContent", (float) 20.36, "category"));
    }

    @DeleteMapping("/beers/{id}")
    void deleteEmployee(@PathVariable Integer id) {
      System.out.println(id);
    }
}
