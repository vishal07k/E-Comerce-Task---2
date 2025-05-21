package com.ecomerce.ecomerce.exception;

public class ProductAlreadyExist extends RuntimeException{
	public ProductAlreadyExist() {
		super("Product is Already Exists !");
	}
}
