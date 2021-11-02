package com.beerhouse.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.beerhouse.entity.Beer;
import com.beerhouse.error.EntityAlreadyExist;
import com.beerhouse.error.EntityNotFound;
import com.beerhouse.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeerService {
    
    @Autowired
	private BeerRepository beerRepository;

    public List<Beer> getBeers() {
        return StreamSupport.stream(beerRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());
	}

    public Beer getBeer(Integer id) {
        Optional<Beer> beer = beerRepository.findById(id);
        if (beer.isEmpty()) throw new EntityNotFound(id, null);
        
        return beer.get();
	}
    
    public Beer insert(Beer beer) {
        Boolean exist = beerRepository.existsById(beer.getId());
        if (exist) throw new EntityAlreadyExist(beer.getId(), null);
		
        return beerRepository.save(beer);
	}

    public void delete(Integer id) {
        this.getBeer(id);
        beerRepository.deleteById(id);
	}

    public Beer update(Beer beer) {
        this.getBeer(beer.getId());
        return beerRepository.save(beer);
	}

    public Beer partialUpdate(Beer beer) {
        Beer oldBeer = this.getBeer(beer.getId());

        if(beer.getName() != null) oldBeer.setName(beer.getName());
		if(beer.getIngredients() != null) oldBeer.setIngredients(beer.getIngredients());	
		if(beer.getAlcoholContent() != null) oldBeer.setAlcoholContent(beer.getAlcoholContent());
		if(beer.getPrice() != null) oldBeer.setPrice(beer.getPrice());
		if(beer.getCategory() != null) oldBeer.setCategory(beer.getCategory());

        return beerRepository.save(oldBeer);
	}

}