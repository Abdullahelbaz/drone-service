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
public class DroneBattaryCapacityDTO implements Serializable {

	private static final long serialVersionUID = 18137207985054624L;

	private Integer battaryCapacity;

}