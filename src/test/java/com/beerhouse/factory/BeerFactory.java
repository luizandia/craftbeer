package com.beerhouse.factory;

import com.beerhouse.entity.Beer;
import com.beerhouse.entity.PartialBeer;

public class BeerFactory {
	
	public static Beer createBeer() {
		return new Beer(
				1, 
				"name", 
				"ingredient", 
				"alcoholContent", 
				(float) 20.00, 
				"category");
	}

    public static Beer createBeerWithIdAndName(Integer id, String name) {
		return new Beer(
				id, 
				name, 
				"ingredient", 
				"alcoholContent", 
				(float) 20.00, 
				"category");
	}

	public static PartialBeer createPartialBeer(String name) {
		PartialBeer partialBeer = new PartialBeer();
		partialBeer.setName(name);
		return partialBeer;
	}

}