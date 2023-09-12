package com.advocate.exceptions;

@SuppressWarnings("serial")
public class InvalidCustomerException extends Exception {

	public InvalidCustomerException (String message) {
		super(message);
	}
}
