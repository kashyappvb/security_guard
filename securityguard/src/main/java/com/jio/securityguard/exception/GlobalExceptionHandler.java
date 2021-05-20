package com.jio.securityguard.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(value= {CustomException.class})
	public ResponseEntity<Object> handleCustomException(CustomException customException)
	{
		LOGGER.error("CustomException : {}",customException);
		List<String> details = new ArrayList<>();
        details.add(customException.getMsg());
        ErrorResponse error = new ErrorResponse("Failed", details,HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= {MaxUploadSizeExceededException.class})
	public ResponseEntity<Object> handleException(MaxUploadSizeExceededException exception)
	{
		LOGGER.error("MaxUploadSizeExceededException : {}",exception);
		List<String> details = new ArrayList<>();
        details.add("File Upload Limit up to 5MB");
        ErrorResponse error = new ErrorResponse("Failed", details,HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleException(Exception exception)
	{
		LOGGER.error("Exception : {}",exception);
		List<String> details = new ArrayList<>();
        details.add(exception.getMessage());
        ErrorResponse error = new ErrorResponse("Failed", details,HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
