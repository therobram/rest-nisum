package com.nisum.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PasswordInvalidNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PasswordInvalidNotFoundException(String password) {
        super("Password inválida: " + password);
    }
}
