package com.musala.droneservice.controller.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.musala.droneservice.exception.DroneException;

import lombok.Data;

@Data
class DroneError {

	   private HttpStatus status;
	   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	   private LocalDateTime timestamp;
	   private String code;
	   private String message;
	   private String debugMessage;
	   
	   public DroneError(DroneMessage msg) {
		   this.code = msg.getCode();
		   this.message = msg.getBusiness();
		   this.debugMessage = msg.getDebug();
	   }

	   private DroneError() {
	       timestamp = LocalDateTime.now();
	   }

	   DroneError(HttpStatus status, Throwable ex) {
	       this();
	       this.status = status;
	       this.message = "Unexpected error";
	       this.debugMessage = ex instanceof DroneException ? ((DroneException)ex).getDebugMessage() : ex.getLocalizedMessage();
	   }

	   DroneError(HttpStatus status, String code, String message, Throwable ex) {
	       this();
	       this.code = code;
	       this.status = status;
	       this.message = message;
	       this.debugMessage = ex instanceof DroneException ? ((DroneException)ex).getDebugMessage() : ex.getLocalizedMessage();
	   }
	}