package com.musala.droneservice.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
public class MedicationDTO implements Serializable {

	private static final long serialVersionUID = 3556503224189854726L;

	private Long medicationId;

	@Pattern(regexp = "^[A-Za-z0-9_-]*"  ,message="must be letter , numbers , - and  _  ")
	@NotNull(message = "medicationName cannot be null")
	private String medicationName; 

	@NotNull(message = "weight cannot be null")
	private Integer weight;
	
	@Pattern(regexp = "^[A-Z0-9_]*"  ,message="must be upper case letters , numbers and  _  ")
	@NotNull(message = "code cannot be null")
	private String code;

	@NotNull(message = "image cannot be null")
	private String image;

}