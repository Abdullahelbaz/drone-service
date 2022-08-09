package com.musala.droneservice.exception;

import org.springframework.http.HttpStatus;

import com.musala.droneservice.controller.error.DroneMessage;

import lombok.Getter;

public class DroneException extends RuntimeException {

	private static final long serialVersionUID = -1169734628844535962L;

	@Getter
	private String code;

	@Getter
	private String debugMessage;

	@Getter
	private HttpStatus status;

	public DroneException() {
		super();
	}

	public DroneException(DroneMessage msg, Object... values) {
		super(String.format(msg.getBusiness(), values));
		this.status = msg.getStatus();
		this.code = msg.getCode();
		this.debugMessage = String.format(msg.getDebug(), values);
	}

	public DroneException(String message) {
		super(message);
	}

	public DroneException(String message, Throwable cause) {
		super(message, cause);
	}

	public DroneException(String message, String debugMessage) {
		super(message);
		this.debugMessage = debugMessage;
	}

	public DroneException(String message, Throwable cause, String debugMessage) {
		super(message, cause);
		this.debugMessage = debugMessage;
	}

}
