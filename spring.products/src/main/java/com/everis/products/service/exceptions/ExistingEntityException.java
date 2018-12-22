package com.everis.products.service.exceptions;

@SuppressWarnings("serial")
public class ExistingEntityException extends Exception{

	public ExistingEntityException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExistingEntityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ExistingEntityException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ExistingEntityException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ExistingEntityException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
