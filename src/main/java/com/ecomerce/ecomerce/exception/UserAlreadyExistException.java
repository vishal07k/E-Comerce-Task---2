package com.ecomerce.ecomerce.exception;

public class UserAlreadyExistException extends RuntimeException{

	public UserAlreadyExistException() {
		super("User is Already Exist With this email");
	}
}
