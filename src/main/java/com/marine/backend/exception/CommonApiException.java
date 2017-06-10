package com.marine.backend.exception;

import org.springframework.http.HttpStatus;

public class CommonApiException extends Exception{

	
	private static final long serialVersionUID = 5621890264131502677L;

	public CommonApiException(){
		super();
	}
	
	public CommonApiException(Exception e){
		super(e);
	}
	
	public CommonApiException(String message, Exception e){
		super(message, e);
	}

	public CommonApiException(String message, HttpStatus internalServerError) {
		super(message);
	}
}
