package com.musala.droneservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.musala.droneservice.config.AppConfigProperties;
import com.musala.droneservice.controller.error.DroneMessage;
import com.musala.droneservice.exception.DroneException;
import com.musala.droneservice.model.Drone;
import com.musala.droneservice.model.DroneState;
import com.musala.droneservice.model.Medication;
import com.musala.droneservice.model.TransactionType;
import com.musala.droneservice.model.TransactionsHistory;
import com.musala.droneservice.model.dto.DroneBattaryCapacityDTO;
import com.musala.droneservice.model.dto.DroneDTO;
import com.musala.droneservice.model.dto.DroneMedicationDTO;
import com.musala.droneservice.repository.DroneRepository;
import com.musala.droneservice.repository.MedicationRepository;
import com.musala.droneservice.repository.TransactionHistoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DroneService {

	@Autowired
	DroneRepository droneRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	MedicationRepository medicationRepository;

	@Autowired
	AppConfigProperties appConfigProperties;

	@Autowired
	TransactionHistoryRepository transactionHistoryRepository;

	// register Drone
	@Transactional
	public DroneDTO registerDrone(DroneDTO droneDTO) {

		log.info(" registerDrone Start droneDTO is " + droneDTO.toString());

		Drone drone = modelMapper.map(droneDTO, Drone.class);

		Drone droneAllreadyExist = droneRepository.findBySerialNumber(drone.getSerialNumber());

		if (droneAllreadyExist != null) {

			log.error(" Allready found  , the drone allready exist");

			throw new DroneException(DroneMessage.DRONE_ALLREADY_EXIST, "");
		}

		if (drone.getBattaryCapacity().intValue() < appConfigProperties.getDroneBattryLimitForLoading().intValue()
				&& drone.getDroneState().getDroneModelTitle().equals(DroneState.STATE_LOADING_TITLE)) {

			log.error(
					" registerDrone can not register the drone with state loading however the battry is lower than the limit   "
							+ appConfigProperties.getDroneBattryLimitForLoading());

			throw new DroneException(DroneMessage.DRONE_BATTARY_LOW_FOR_LOADING,
					appConfigProperties.getDroneBattryLimitForLoading().toString());
		}

		drone = droneRepository.save(drone);
		DroneDTO droneDTOReturning = modelMapper.map(drone, DroneDTO.class);

		TransactionType transactionType = new TransactionType();
		transactionType.setTransactionTypeId(TransactionType.TRANSACTION_TYPE_REGISTER_DRONE);
		createTransactionsHistory(transactionType, droneDTO.toString(), droneDTO.getSerialNumber());

		log.info(" registerDrone End");
		return droneDTOReturning;
	}

	// loading medication for drone which provided
	@Transactional
	public DroneMedicationDTO loadDroneWithMedication(@Valid DroneMedicationDTO droneMedicationDTO) {

		log.info(" loadDroneWithMedication Start droneMedicationDTO is " + droneMedicationDTO.toString());

		Drone drone = modelMapper.map(droneMedicationDTO, Drone.class);
		String medicationNames = "";
		
		// check if the drone exist or not
		if (droneRepository.existsById(drone.getDroneId())) {

			log.info(" loadDroneWithMedication drone exist and is " + drone.toString());

			Set<Medication> droneMedication = drone.getMedicationList();

			// check if the medication exist for loading or not
			if (droneMedication != null && droneMedication.size() > 0) {

				log.info(" loadDroneWithMedication > droneMedication != null && droneMedication.size() > 0 ");
				Float weightSum = 0f;
				
				for (Medication medication : droneMedication) {

					// will save medication if not saved before
					// then will load medication for the drone
					log.info(" loadDroneWithMedication medication. is  " + medication.toString());

					List<Medication> medicationReturning = new ArrayList<>();

					medicationReturning = medicationRepository.findByMedicationName(medication.getMedicationName());

					if (medication.getMedicationId() == null && medicationReturning.size() <= 0) {
						log.info(" loadDroneWithMedication > medication is not exist will start saving ");
						medicationRepository.save(medication);
					} else {

						log.info(" loadDroneWithMedication > medication  exist will start saving ");

						medication.setMedicationId(medicationReturning.get(0).getMedicationId());
						medicationRepository.save(medication);
					}
					weightSum += medication.getWeight();

					medicationNames += " , Medication name : " + medication.getMedicationName() + " ,Medication Id :  "
							+ medication.getMedicationId();
				}
				if (weightSum > drone.getWeightLimit()) {
					log.error(
							" loadDroneWithMedication loaded medication weight is larger than  the drone availability which is "
									+ drone.getWeightLimit());

					throw new DroneException(DroneMessage.MEDICATION_LOADED_LARGER_THAN_THE_CORRECT_WEIGHT,
							drone.getWeightLimit().toString());
				}
				drone = droneRepository.save(drone);
			} else {
				log.error(" loadDroneWithMedication drone not exist ");

				throw new DroneException(DroneMessage.NO_MEDICATION_FOUND_FOR_LOADING, "");

			}

		} else {
			log.error(" loadDroneWithMedication drone not exist ");

			throw new DroneException(DroneMessage.NO_DRONE_FOUND_FOR_LOADING, "");
		}
		DroneMedicationDTO droneMedicationDTOReturning = modelMapper.map(drone, DroneMedicationDTO.class);

		TransactionType transactionType = new TransactionType();
		transactionType.setTransactionTypeId(TransactionType.TRANSACTION_TYPE_LOAD_MEDICATION);
		String requestDetails = " drone Serial is " +drone.getSerialNumber() + medicationNames ;
		createTransactionsHistory(transactionType,requestDetails , drone.getSerialNumber());

		log.info(" droneMedicationDTOReturning returning medication. is  " + droneMedicationDTOReturning.toString());

		return droneMedicationDTOReturning;
	}

	// get drone With Medications
	public DroneMedicationDTO getdroneWithMedications(String droneSerialNumber) {

		log.info(" getdroneWithMedications Start");
		Drone drone = new Drone();
		drone = droneRepository.findBySerialNumber(droneSerialNumber);

		if (drone == null) {
			log.info(" getdroneWithMedications >  no drone found");
			throw new DroneException(DroneMessage.NO_DRONE_FOUND, "");
		}

		DroneMedicationDTO droneMedicationDTO = modelMapper.map(drone, DroneMedicationDTO.class);

		log.info(" getdroneWithMedications droneMedicationDTOis " + droneMedicationDTO.toString()
				+ " and getdroneWithMedications End");

		TransactionType transactionType = new TransactionType();
		transactionType.setTransactionTypeId(TransactionType.TRANSACTION_TYPE_FINE_BY_GIVEN_DRONE);
		createTransactionsHistory(transactionType,"Find by serial done" , droneSerialNumber);
		
		return droneMedicationDTO;
	}

	// get drone With Medications Available For Loading
	public List<DroneMedicationDTO> getdroneWithMedicationsAvailableForLoading() {

		log.info(" getdroneWithMedicationsAvailableForLoading Start");
		List<Drone> droneList = new ArrayList<Drone>();
		droneList = droneRepository.findAll();

		if (droneList == null || droneList.size() <= 0) {
			log.info(" getdroneWithMedicationsAvailableForLoading > 'droneList == null || droneList.size() <= 0'");
			throw new DroneException(DroneMessage.NO_DRONE_FOUND, "");
		}

		List<DroneMedicationDTO> droneMedicationDTOList = new ArrayList<DroneMedicationDTO>();

		for (int i = 0; i < droneList.size(); i++) {
			Float weightSum = 0f;

			if (droneList.get(i).getDroneState().getDroneModelTitle().equals(DroneState.STATE_IDLE_TITLE)) {
				log.info(" getdroneWithMedicationsAvailableForLoading  droneList " + droneList.get(i).toString()
						+ " and state is STATE_IDLE_TITLE");

				Set<Medication> medicationList = new HashSet<Medication>();
				medicationList = droneList.get(i).getMedicationList();

				for (Medication medication : medicationList) {
					weightSum += medication.getWeight();
					log.info(" getdroneWithMedicationsAvailableForLoading  medication " + medication.toString());

				}

				if (weightSum < appConfigProperties.getMedicationRequiredWeight()) {
					log.info(" getdroneWithMedicationsAvailableForLoading  Sum weight finally is weightSum "
							+ weightSum);

					DroneMedicationDTO droneMedicationDTO = modelMapper.map(droneList.get(i), DroneMedicationDTO.class);
					droneMedicationDTOList.add(droneMedicationDTO);
				}
			}
		}

		TransactionType transactionType = new TransactionType();
		transactionType.setTransactionTypeId(TransactionType.TRANSACTION_TYPE_DRONES_AVAILABLE);
		createTransactionsHistory(transactionType,"available drone" ," size of available drones"+ droneMedicationDTOList.size());
		
		log.info(" getdroneWithMedicationsAvailableForLoading droneMedicationDTOList size is "
				+ droneMedicationDTOList.size() + " and getdroneWithMedicationsAvailableForLoading End");

		return droneMedicationDTOList;
	}

	// get drone Battary Capacity
	public DroneBattaryCapacityDTO getdroneBattaryCapacity(String droneSerialNumber) {

		log.info(" getdroneBattaryCapacity Start");
		Drone drone = new Drone();
		drone = droneRepository.findBySerialNumber(droneSerialNumber);

		if (drone == null) {
			log.info(" getdroneBattaryCapacity >  no drone found");
			throw new DroneException(DroneMessage.NO_DRONE_FOUND, "");
		}
		DroneBattaryCapacityDTO droneBattaryCapacityDTO = new DroneBattaryCapacityDTO();
		droneBattaryCapacityDTO.setBattaryCapacity(drone.getBattaryCapacity());
		
		TransactionType transactionType = new TransactionType();
		transactionType.setTransactionTypeId(TransactionType.TRANSACTION_TYPE_CHECK_BATTARY_CAPACITY);
		createTransactionsHistory(transactionType,droneBattaryCapacityDTO.toString() , droneSerialNumber);
		
		
		log.info(" getdroneBattaryCapacity End");

		return droneBattaryCapacityDTO;
	}

	private TransactionsHistory createTransactionsHistory(TransactionType transactionType, String requestDetails,
			String droneDetails) {

		TransactionsHistory transactionsHistory = new TransactionsHistory();

		transactionsHistory.setTransactionDate(LocalDateTime.now());
		transactionsHistory.setRequestDetails(requestDetails);
		transactionsHistory.setTransactionType(transactionType);
		transactionsHistory.setDroneDetails(droneDetails);

		transactionHistoryRepository.save(transactionsHistory);

		return transactionsHistory;
	}

}
