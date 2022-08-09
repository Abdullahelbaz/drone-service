package com.musala.droneservice.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Validated
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneMedicationDTO implements Serializable {

	private static final long serialVersionUID = 18137207985054624L;

	@NotNull(message = "reference droneId cannot be null")
	private Long droneId;

	@NotNull(message = "serialNumber cannot be null")
	@Size(max=100 ,message = " 100 char max")
	private String serialNumber;

	@NotNull(message = "droneModel cannot be null")
	private DroneModelDTO droneModel;

	@NotNull(message = "droneState cannot be null")
	private DroneStateDTO droneState;

	@NotNull(message = "battaryCapacity cannot be null")
	private Integer battaryCapacity;

	@NotNull(message = "weightLimit cannot be null")
	@Max(500)
	private Integer weightLimit;
	
	@NotNull(message = "loaded medicationList  cannot be null")
	@Valid
	private Set<MedicationDTO> medicationList = new HashSet<MedicationDTO> ();

}