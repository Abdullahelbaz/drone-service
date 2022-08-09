package com.musala.droneservice.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.droneservice.controller.DroneController;
import com.musala.droneservice.controller.error.DroneMessage;
import com.musala.droneservice.exception.DroneException;
import com.musala.droneservice.model.dto.DroneDTO;
import com.musala.droneservice.model.dto.DroneMedicationDTO;
import com.musala.droneservice.service.DroneService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = { "*" }, allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/drone/")
@Slf4j
public class DroneControllerImpl implements DroneController {

	@Autowired
	DroneService droneService;

	@Override
	public ResponseEntity<?> registerDrone(@Valid DroneDTO droneDTO, BindingResult bindingResult) {
		bindingResultValidation(bindingResult);
		return ResponseEntity.ok().body(droneService.registerDrone(droneDTO));
	}

	@Override
	public ResponseEntity<?> loadDroneWithMedication(@Valid DroneMedicationDTO droneMedicationDTO,
			BindingResult bindingResult) {
		bindingResultValidation(bindingResult);
		return ResponseEntity.ok().body(droneService.loadDroneWithMedication(droneMedicationDTO));
	}

	@Override
	public ResponseEntity<?> getdroneWithMedications(String droneSerialNumber) {

		return ResponseEntity.ok().body(droneService.getdroneWithMedications(droneSerialNumber));
	}

	@Override
	public ResponseEntity<?> getdroneWithMedicationsAvailableForLoading() {

		return ResponseEntity.ok().body(droneService.getdroneWithMedicationsAvailableForLoading());
	}

	@Override
	public ResponseEntity<?> getdroneBattaryCapacity(String droneSerialNumber) {
		return ResponseEntity.ok().body(droneService.getdroneBattaryCapacity(droneSerialNumber));
	}

	public void bindingResultValidation(BindingResult bindingResult) {
		log.info("bindingResultValidation Validation started  ");

		log.info(" bindingResultValidation   bindingResult is " + bindingResult.toString());

		String errorMessage = "";

		if (bindingResult.hasErrors()) {

			log.error(" bindingResultValidation  bindingResult.hasErrors() we will loop for it ");

			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMessage += " ,this field (" + error.getField() + ") has this error ( " + error.getDefaultMessage()
						+ ")";
				log.error("errorMessage" + errorMessage);
			}

			log.error("bindingResultValidation KPI_INPUT_HAS_GENERIC_EXCEPTION APIException  with this message  ..."
					+ errorMessage);
			throw new DroneException(DroneMessage.DRONE_REQUEST_VALIDATION_INCORRECT, errorMessage);

		} else {
			log.info("bindingResultValidation Validation without any Exceptions End  ");

		}
		log.info("bindingResultValidation Validation End  ");

	}

}
