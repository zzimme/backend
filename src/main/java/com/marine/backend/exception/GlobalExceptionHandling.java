package com.marine.backend.exception;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.rest.webmvc.support.ExceptionMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class GlobalExceptionHandling {
    protected Logger logger;

    public GlobalExceptionHandling() {
        logger = LoggerFactory.getLogger(getClass());
    }
 
	@ResponseBody
	public ResponseEntity<?> handleUnauthenticationException(Exception e) {
	    return errorResponse(e, HttpStatus.BAD_REQUEST);
	}
 
    @ExceptionHandler({DataIntegrityViolationException.class, SQLIntegrityConstraintViolationException.class})
    @ResponseBody
    public ResponseEntity<?> handleConflictException(Exception e) {
        return errorResponse(e, HttpStatus.CONFLICT);
    }
    
    

    @ExceptionHandler({ ResourceNotFoundException.class, NoHandlerFoundException.class })
    @ResponseBody
    public ResponseEntity<?> handleNotFoundException(Exception e) {
        return errorResponse(e, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler({ SQLException.class, DataAccessException.class, RuntimeException.class })
    @ResponseBody
    public ResponseEntity<?> handleSQLException(Exception e) {
        return errorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
	@ExceptionHandler({ IOException.class, ParseException.class,  JsonParseException.class, JsonMappingException.class })
	@ResponseBody
	public ResponseEntity<?> handleParseException(Exception e) {
		return errorResponse(e, HttpStatus.BAD_REQUEST);
	}
 
   @ExceptionHandler({ InvalidKeyException.class, NoSuchAlgorithmException.class })
   @ResponseBody
      public ResponseEntity<?> handleHashException(Exception e) {
        return errorResponse(new Exception("Encrypt/Decrypt key is requested"), HttpStatus.LOCKED);
    }
 
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ResponseEntity<?> handleAnyException(Exception e) {
		return errorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
   
    protected ResponseEntity<ExceptionMessage> errorResponse(Throwable throwable,
            HttpStatus status) {
        if (null != throwable) {
            return response(new ExceptionMessage(throwable), status);
        } else {
            return response(null, status);
        }
    }

    protected <T> ResponseEntity<T> response(T body, HttpStatus status) {
        return new ResponseEntity<T>(body, new HttpHeaders(), status);
    }
}

