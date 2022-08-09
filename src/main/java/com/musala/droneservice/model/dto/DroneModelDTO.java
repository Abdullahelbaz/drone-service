package com.musala.droneservice.model.dto;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Validated
@ToString
@Data
@NoArgsConstructor
public class DroneModelDTO implements Serializable {

	private static final long serialVersionUID = -175037670685065155L;

	private Long droneModelId;

	private String droneModelCaption;

	private String droneModelTitle;

}