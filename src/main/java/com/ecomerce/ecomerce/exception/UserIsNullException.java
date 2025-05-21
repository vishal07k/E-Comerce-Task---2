package com.ecomerce.ecomerce.exception;

public class UserIsNullException extends RuntimeException{

	public UserIsNullException() {
		super("User is null ! Something is wrong");
	}
	
}
