package com.musala.droneservice.controller.error;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class DroneMessage {
	
	private HttpStatus status;
	
	private String code;
	
	private String business;
	
	private String debug;
	
	
	public DroneMessage(HttpStatus status, String code, String business, String debug) {
		this.status = status;
		this.code = code;
		this.business = business;
		this.debug = debug;
	}
	

	
	public static final DroneMessage DRONE_REQUEST_VALIDATION_INCORRECT = new DroneMessage(//
			HttpStatus.CONFLICT//
			, "DRN-REQ-0001"//
			, "Exception on DRONE Input with this message [%s] !"//
			, "please handle this meesgae [%s] ");
	
	public static final DroneMessage NO_DRONE_FOUND = new DroneMessage(
			HttpStatus.NOT_FOUND
			, "DRN-REQ-0002"
			, "No DRONE found !"
			, "No DRONE found !"
			);
	public static final DroneMessage NO_DRONE_FOUND_FOR_LOADING = new DroneMessage(
			HttpStatus.NOT_FOUND
			, "DRN-REQ-0003"
			, "No DRONE found  FOR loading !"
			, "No DRONE found FOR loading !"
			);
	public static final DroneMessage NO_MEDICATION_FOUND_FOR_LOADING = new DroneMessage(
			HttpStatus.NOT_FOUND
			, "DRN-REQ-0004"
			, "No MEDICATION found  FOR loading !"
			, "No MEDICATION found FOR loading !"
			);
	
	public static final DroneMessage MEDICATION_LOADED_LARGER_THAN_THE_CORRECT_WEIGHT = new DroneMessage(//
			HttpStatus.CONFLICT//
			, "DRN-REQ-0005"//
			, "Exception on DRONE loaded medication weight lager than  [%s] !"//
			, "Exception on DRONE loaded medication weight lager than  [%s] !");
	
	public static final DroneMessage DRONE_BATTARY_LOW_FOR_LOADING = new DroneMessage(//
			HttpStatus.CONFLICT//
			, "DRN-REQ-0005"//
			, "Exception on DRONE battary lower than required  [%s] ! Hoever the state is loading "//
			, "Exception on DRONE battary lower than required  [%s] ! Hoever the state is loading ");
	
	public static final DroneMessage DRONE_ALLREADY_EXIST = new DroneMessage(
			HttpStatus.FOUND
			, "DRN-REQ-0006"
			, "The Drone Allreay Exist!"
			, "The Drone Allreay Exist!"
			);
	
}
