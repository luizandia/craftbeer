package com.beerhouse.error;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntityAlreadyExist extends RuntimeException {

	private final Integer id;

	public EntityAlreadyExist(Integer id,  @Nullable String mensagem) {
		super(mensagem);
		this.id = id;
	}
	
	public Integer geId() {
		return this.id;
	}

}