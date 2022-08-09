package com.musala.droneservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.musala.droneservice.model.Drone;

public interface DroneRepository extends PagingAndSortingRepository<Drone, Long>, JpaRepository<Drone, Long> {

	Drone findBySerialNumber(String droneSerialNumber);

}
