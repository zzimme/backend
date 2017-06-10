package com.marine.backend;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", ex.getMessage());
		map.put("status", status);

		return new ResponseEntity<Object>(map, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", ex.getMessage());
		map.put("status", status);

		return new ResponseEntity<Object>(map, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", ex.getMessage());
		map.put("status", status);

		return new ResponseEntity<Object>(map, headers, status);

	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", ex.getMessage());
		map.put("status", status);

		return new ResponseEntity<Object>(map, headers, status);

	}
	
	

}
