package com.musala.droneservice.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.ToString;

@Validated
@Entity
@Table(name = "drone")
@EntityListeners(AuditingEntityListener.class)
@Data
@ToString
public class Drone implements Serializable {


	private static final long serialVersionUID = -8316296377932111913L;

	@Id
	@Column(name = "drone_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "hib_drone_id_seq")
	@SequenceGenerator(name = "hib_drone_id_seq", sequenceName = "drone_id_seq", allocationSize = 1)
	private Long droneId;

	@Column(name = "serial_number")
	private String serialNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "drone_model_id", nullable = true)
	private DroneModel droneModel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "drone_state_id", nullable = true)
	private DroneState droneState;

	@Column(name = "battary_capacity")
	private Integer battaryCapacity;

	@Column(name = "weight_limit")
	private Integer weightLimit;
	
	@ManyToMany
	@JoinTable(
	  name = "drone_medication", 
	  joinColumns = @JoinColumn(name = "drone_id"), 
	  inverseJoinColumns = @JoinColumn(name = "medication_id"))
	private Set<Medication> medicationList;

}