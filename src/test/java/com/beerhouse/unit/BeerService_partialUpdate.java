package com.beerhouse.unit;

import java.util.Optional;

import com.beerhouse.entity.Beer;
import com.beerhouse.entity.PartialBeer;
import com.beerhouse.error.EntityNotFound;
import com.beerhouse.factory.BeerFactory;
import com.beerhouse.repository.BeerRepository;
import com.beerhouse.service.BeerService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class BeerService_partialUpdate {

	@Mock
	private BeerRepository beerRepository;
	
	@InjectMocks
	private BeerService beerService;
	
	@Test
	public void getBeerShouldReturnExpectedBeer() {
        Beer oldBeer = BeerFactory.createBeer();
        PartialBeer partialBeer = BeerFactory.createPartialBeer("name2");
		
		Mockito.when(beerRepository.findById(oldBeer.getId())).thenReturn(Optional.of(oldBeer));
		Optional.ofNullable(partialBeer.getName()).ifPresent(oldBeer::setName);
        Mockito.when(beerRepository.save(oldBeer)).thenReturn(oldBeer);
        
		Beer result = beerService.partialUpdate(partialBeer, oldBeer.getId());
		Assertions.assertEquals(partialBeer.getName(), result.getName());
		Assertions.assertEquals(oldBeer.getId(), result.getId());
		Assertions.assertEquals(oldBeer.getPrice(), result.getPrice());
		Assertions.assertEquals(oldBeer.getAlcoholContent(), result.getAlcoholContent());
		Assertions.assertEquals(oldBeer.getCategory(), result.getCategory());
		Assertions.assertEquals(oldBeer.getIngredients(), result.getIngredients());

		Mockito.verify(beerRepository, Mockito.times(1)).findById(oldBeer.getId());
        Mockito.verify(beerRepository, Mockito.times(1)).save(oldBeer);
	}

	@Test
	public void partialUpdateBeerShouldNotUpdateAndThrowEntityNotFound() {
        Beer oldBeer = BeerFactory.createBeer();
        PartialBeer partialBeer = BeerFactory.createPartialBeer("name2");
        Mockito.when(beerRepository.findById(oldBeer.getId())).thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(EntityNotFound.class, () -> {
			beerService.partialUpdate(partialBeer, oldBeer.getId());
		});
        
		Mockito.verify(beerRepository, Mockito.times(1)).findById(oldBeer.getId());
	}

}