package com.exception;

public class IncorrectAmountException extends RuntimeException {

	public IncorrectAmountException() {
		super();
	}

	public IncorrectAmountException(String message) {
		super(message);
	}

}
