package com.beerhouse.repository;

import com.beerhouse.entity.Beer;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface BeerRepository extends CrudRepository<Beer, Integer> {

  Optional<Beer> findById(Integer id);

}
