package com.beerhouse.unit;

import com.beerhouse.entity.Beer;
import com.beerhouse.error.EntityAlreadyExist;
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
public class BeerService_insert {

	@Mock
	private BeerRepository beerRepository;
	
	@InjectMocks
	private BeerService beerService;
	
	@Test
	public void insertShouldInsertAndReturnExpectedBeer() {
		Beer expectBeer = BeerFactory.createBeer();
		Mockito.when(beerRepository.existsById(expectBeer.getId())).thenReturn(false);
		Mockito.when(beerRepository.save(expectBeer)).thenReturn(expectBeer);

		Beer result = beerService.insert(expectBeer);
		Assertions.assertEquals(expectBeer, result);

		Mockito.verify(beerRepository, Mockito.times(1)).existsById(expectBeer.getId());
        Mockito.verify(beerRepository, Mockito.times(1)).save(expectBeer);
	}

    @Test
	public void insertShouldNotInsertAndThrowEntityAlreadyExist() {
		Beer expectBeer = BeerFactory.createBeer();
		Mockito.when(beerRepository.existsById(expectBeer.getId())).thenReturn(true);

        Assertions.assertThrows(EntityAlreadyExist.class, () -> {
            beerService.insert(expectBeer);
		});

		Mockito.verify(beerRepository, Mockito.times(1)).existsById(expectBeer.getId());
        Mockito.verify(beerRepository, Mockito.times(0)).save(expectBeer);
	}

}