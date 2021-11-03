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
public class BeerService_getBeer {

	@Mock
	private BeerRepository beerRepository;
	
	@InjectMocks
	private BeerService beerService;
	
	@Test
	public void getBeerShouldReturnExpectedBeer() {
		Beer expectBeer = BeerFactory.createBeer();
		Mockito.when(beerRepository.findById(expectBeer.getId())).thenReturn(Optional.of(expectBeer));

		Beer result = beerService.getBeer(expectBeer.getId());

		Assertions.assertEquals(expectBeer, result);

		Mockito.verify(beerRepository, Mockito.times(1)).findById(expectBeer.getId());
	}

    @Test
	public void getBeerShouldThrowEntityNotFound() {
		Integer id = 1;
		Mockito.when(beerRepository.findById(id)).thenReturn(Optional.ofNullable(null));

        Assertions.assertThrows(EntityNotFound.class, () -> {
			beerService.getBeer(id);
		});

		Mockito.verify(beerRepository, Mockito.times(1)).findById(id);
	}
	
}