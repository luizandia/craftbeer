package com.beerhouse.error;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFound extends RuntimeException {

	private final Integer id;

	public EntityNotFound(Integer id,  @Nullable String mensagem) {
		super(mensagem);
		this.id = id;
	}
	
	public Integer geId() {
		return this.id;
	}

}