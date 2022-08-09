package com.musala.droneservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.ToString;

@Validated
@Entity
@Table(name = "medication")
@EntityListeners(AuditingEntityListener.class)
@Data
@ToString
public class Medication implements Serializable {

	private static final long serialVersionUID = 5057388942388599423L;

	@Id
	@Column(name = "medication_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "hib_medication_id_seq")
	@SequenceGenerator(name = "hib_medication_id_seq", sequenceName = "medication_id_seq", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medicationId;

	@Column(name = "medication_name")
	private String medicationName;

	@Column(name = "weight")
	private Integer weight;

	@Column(name = "code")
	private String code;

	@Column(name = "image")
	private String image;

}