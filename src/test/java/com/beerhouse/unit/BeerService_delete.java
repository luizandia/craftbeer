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
public class BeerService_delete {

	@Mock
	private BeerRepository beerRepository;
	
	@InjectMocks
	private BeerService beerService;
	
	@Test
	public void shouldDeleteBeer() {
		Beer beer = BeerFactory.createBeer();
		Mockito.when(beerRepository.findById(beer.getId())).thenReturn(Optional.of(beer));
		Mockito.doNothing().when(beerRepository).deleteById(beer.getId());

		beerService.delete(beer.getId());

		Mockito.verify(beerRepository, Mockito.times(1)).findById(beer.getId());
		Mockito.verify(beerRepository, Mockito.times(1)).deleteById(beer.getId());
	}

    @Test
	public void shouldNotDeleteBeerAndThrowEntityNotFound() {
		Beer beer = BeerFactory.createBeer();
        Mockito.when(beerRepository.findById(beer.getId())).thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(EntityNotFound.class, () -> {
            beerService.delete(beer.getId());
		});

		Mockito.verify(beerRepository, Mockito.times(1)).findById(beer.getId());
		Mockito.verify(beerRepository, Mockito.times(0)).deleteById(beer.getId());
	}

}