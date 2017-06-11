package com.marine.backend.exception;

import org.springframework.http.HttpStatus;

public class NotSupportedTypeException extends Exception{

	
	private static final long serialVersionUID = 5400101673477452769L;
	

	public NotSupportedTypeException(){
		super();
	}
	
	public NotSupportedTypeException(Exception e){
		super(e);
	}
	

	public NotSupportedTypeException(String message, HttpStatus internalServerError) {
		super(message);
	}
	

}
