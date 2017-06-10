package com.marine.backend.exception;

import org.springframework.http.HttpStatus;

public class NotExistItemException  extends Exception{

	private static final long serialVersionUID = 4180687133848910338L;

	public NotExistItemException(){
		super();
	}
	
	public NotExistItemException(Exception e){
		super(e);
	}
	
	public NotExistItemException(String message, Exception e){
		super(message, e);
	}

	public NotExistItemException(String message, HttpStatus internalServerError) {
		super(message);
	}
}

