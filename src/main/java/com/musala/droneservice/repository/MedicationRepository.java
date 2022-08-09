package com.musala.droneservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.musala.droneservice.model.Medication;

public interface MedicationRepository
		extends PagingAndSortingRepository<Medication, Long>, JpaRepository<Medication, Long> {

	List<Medication> findByMedicationName(String medicationName);

}
