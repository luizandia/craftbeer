package com.beerhouse.unit;

import java.util.Optional;

import com.beerhouse.entity.Beer;
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
public class BeerService_update {

	@Mock
	private BeerRepository beerRepository;
	
	@InjectMocks
	private BeerService beerService;
	
	@Test
	public void updateBeerShouldUpdateAndReturnExpectedBeer() {
		Beer expectBeer = BeerFactory.createBeer();
		Mockito.when(beerRepository.findById(expectBeer.getId())).thenReturn(Optional.of(expectBeer));
        Mockito.when(beerRepository.save(expectBeer)).thenReturn(expectBeer);

		Beer result = beerService.update(expectBeer);
		Assertions.assertEquals(expectBeer, result);

		Mockito.verify(beerRepository, Mockito.times(1)).findById(expectBeer.getId());
        Mockito.verify(beerRepository, Mockito.times(1)).save(expectBeer);
	}

    @Test
	public void updateBeerShouldNotUpdateAndThrowEntityNotFound() {
		Beer expectBeer = BeerFactory.createBeer();
		Mockito.when(beerRepository.findById(expectBeer.getId())).thenReturn(Optional.ofNullable(null));
        Mockito.when(beerRepository.save(expectBeer)).thenReturn(expectBeer);

        Assertions.assertThrows(EntityNotFound.class, () -> {
            beerService.update(expectBeer);
		});

		Mockito.verify(beerRepository, Mockito.times(1)).findById(expectBeer.getId());
        Mockito.verify(beerRepository, Mockito.times(0)).save(expectBeer);
	}

}