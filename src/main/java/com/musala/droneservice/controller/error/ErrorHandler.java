package com.musala.droneservice.controller.error;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.musala.droneservice.exception.DroneException;

import lombok.extern.slf4j.Slf4j;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ErrorHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(DroneException.class)
	public ResponseEntity<Object> handleAPIException(DroneException ex) {
		log.error("Business Error thrown: ", ex);
		DroneError apiError = new DroneError(ex.getStatus(), ex.getCode(), ex.getMessage(), ex);
		return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex) {
		log.error("Technical Error thrown: ", ex);
		DroneError apiError = new DroneError(HttpStatus.INTERNAL_SERVER_ERROR, "00000", "Internal System 'Drone' Error", ex);
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
}
