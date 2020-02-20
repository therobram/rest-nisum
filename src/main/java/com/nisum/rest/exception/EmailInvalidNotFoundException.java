package com.nisum.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmailInvalidNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailInvalidNotFoundException(String email) {
        super("Email inválido: " + email);
    }
}
