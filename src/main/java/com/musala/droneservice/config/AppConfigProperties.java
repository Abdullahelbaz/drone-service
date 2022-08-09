package com.musala.droneservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@ConfigurationProperties(prefix = "")
@Getter
@Order(value = 1)
public class AppConfigProperties {

	
	@Value("${drone.medication.weight.required}")
	Float medicationRequiredWeight;
	
	@Value("${drone.battary.limit.required}")
	Integer droneBattryLimitForLoading;

	

}
