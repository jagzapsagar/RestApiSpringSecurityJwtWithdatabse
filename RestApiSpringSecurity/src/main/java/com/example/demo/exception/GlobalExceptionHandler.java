package com.example.demo.exception;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		Map<String, String> errors = new HashMap<>();

		for (FieldError error : result.getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFound(UserNotFoundException e) {
		ErrorResponse er = new ErrorResponse();
		er.setMessage(e.getMessage());
		er.setTimestamp(LocalDate.now().toString());
		er.setDetails("--User Not Found--");
		return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);// HTTP 404 Not Found

	}

}
