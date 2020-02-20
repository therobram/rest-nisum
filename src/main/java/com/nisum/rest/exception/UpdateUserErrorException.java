package com.nisum.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UpdateUserErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UpdateUserErrorException() {
        super("No se pudo crear el usuario");
    }
}
