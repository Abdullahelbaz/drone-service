package com.musala.droneservice.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.musala.droneservice.model.dto.DroneDTO;
import com.musala.droneservice.model.dto.DroneMedicationDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "Drone API" })
public interface DroneController {

	@ApiOperation("Register drones")
	@PostMapping(value = "")
	public ResponseEntity<?> registerDrone(@Valid @RequestBody DroneDTO droneDTO, BindingResult bindingResult);

	@ApiOperation("loading medication on drones")
	@PostMapping(value = "/loadmedication")
	public ResponseEntity<?> loadDroneWithMedication(@Valid @RequestBody DroneMedicationDTO droneDTO,
			BindingResult bindingResult);

	@ApiOperation("returning drones with the medications")
	@GetMapping(value = "")
	public ResponseEntity<?> getdroneWithMedications(@RequestParam(required = true) String droneSerialNumber);

	@ApiOperation("returning drones available for loading")
	@GetMapping(value = "/available")
	public ResponseEntity<?> getdroneWithMedicationsAvailableForLoading();

	@ApiOperation("returning drones battary capacity")
	@GetMapping(value = "/checkbattary")
	public ResponseEntity<?> getdroneBattaryCapacity(@RequestParam(required = true) String droneSerialNumber);

	/*
	 * @ApiOperation("KPI Settings List")
	 * 
	 * @GetMapping(value="") public List<KPISettingsDTO>
	 * getKPISettingsDTOs(@RequestHeader(value = "subsidiaryFilter", required =
	 * false) String subsidiaryFilter);
	 * 
	 * @ApiOperation("KPI Settings Details")
	 * 
	 * @GetMapping(value="/details") public KPISettingsDetailsDTO
	 * getKPISettingsDetails(@RequestHeader(value = "subsidiaryFilter", required =
	 * false) String subsidiaryFilter, String kpiId);
	 * 
	 * @ApiOperation("Jira Tickets")
	 * 
	 * @GetMapping(value="/jira") public List<KPISettingsJiraTicketsDTO>
	 * getKPIJiraTicketsDTO(String kpiId);
	 * 
	 * @ApiOperation("KPI Settings Insert")
	 * 
	 * @PostMapping(value="/insert") void insertKPISettings(@RequestBody
	 * KPISettingsWriteDTO dto);// throws Exception;
	 * 
	 * @ApiOperation("KPI Settings Update")
	 * 
	 * @PostMapping(value="/update") void updateKPISettings(@RequestBody
	 * KPISettingsWriteDTO dto);
	 * 
	 * @ApiOperation("KPI Settings bu lookup")
	 * 
	 * @GetMapping(value="/lookup") public List<String> critetriaBu();
	 * 
	 * @ApiOperation("KPI Settings sub bu lookup")
	 * 
	 * @GetMapping(value="/lookup/{bu}") public List<String>
	 * critetriaSubBu(@PathVariable String bu);
	 * 
	 * @ApiOperation("KPI Settings function lookup")
	 * 
	 * @GetMapping(value="/lookup/{bu}/{subUnit}") public List<String>
	 * critetriaFunction(@PathVariable String bu , @PathVariable String subUnit);
	 * 
	 * @ApiOperation("KPI Settings type lookup")
	 * 
	 * @GetMapping(value="/lookup/{bu}/{subUnit}/{function}") public List<String>
	 * critetriaType(@PathVariable String bu , @PathVariable String subUnit
	 * , @PathVariable String function);
	 * 
	 * @ApiOperation("KPI Settings group lookup")
	 * 
	 * @GetMapping(value="/lookupType/{bu}/{subUnit}/{function}") public
	 * List<String> critetriaGroup(@PathVariable String bu , @PathVariable String
	 * subUnit , @PathVariable String function, @RequestParam String type);
	 * 
	 * @ApiOperation("search owners/custodians")
	 * 
	 * @GetMapping(value="/owners") public List<KPIOwnerDTO>
	 * getOwnersStartingWith(@RequestParam String startingWith);
	 * 
	 */
}
