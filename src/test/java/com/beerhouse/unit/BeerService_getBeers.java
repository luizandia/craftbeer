package com.beerhouse.unit;

import java.util.List;

import com.beerhouse.entity.Beer;
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
public class BeerService_getBeers {

	@Mock
	private BeerRepository beerRepository;
	
	@InjectMocks
	private BeerService beerService;
	
	@Test
	public void getBeersShouldReturnAllBeers() {
		Beer beer1 = BeerFactory.createBeer();
		Beer beer2 = BeerFactory.createBeerWithIdAndName(2, "name2");
		List<Beer> expected = List.of(beer1, beer2);
		Mockito.when(beerRepository.findAll()).thenReturn(expected);

		List<Beer> result = beerService.getBeers();
		Assertions.assertEquals(expected, result);

		Mockito.verify(beerRepository, Mockito.times(1)).findAll();
	}

	@Test
	public void getBeersShouldReturnEmpty() {
		List<Beer> expected = List.of();
		Mockito.when(beerRepository.findAll()).thenReturn(expected);

		List<Beer> result = beerService.getBeers();
		Assertions.assertEquals(expected, result);

		Mockito.verify(beerRepository, Mockito.times(1)).findAll();
	}
	
}