package com.marine.backend.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistItemException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5990578259831677442L;
	
	public AlreadyExistItemException(){
		super();
	}
	
	public AlreadyExistItemException(Exception e){
		super(e);
	}
	
	public AlreadyExistItemException(String message, Exception e){
		super(message, e);
	}

	public AlreadyExistItemException(String message, HttpStatus internalServerError) {
		super(message);
	}
	

}
