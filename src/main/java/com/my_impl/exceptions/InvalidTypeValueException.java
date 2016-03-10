package com.my_impl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class InvalidTypeValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 340806927321742833L;

	public InvalidTypeValueException(String message) {
		super(message);
	}
}
