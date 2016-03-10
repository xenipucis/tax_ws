package com.my_impl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST,reason="You need to provide a double value greater than 0 for 'ValueOfAmount'")
public class InvalidAmountValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -101583399777780444L;

}
