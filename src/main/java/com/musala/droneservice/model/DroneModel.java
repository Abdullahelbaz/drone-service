package com.musala.droneservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.ToString;

@Validated
@Entity
@Table(name = "drone_model")
@EntityListeners(AuditingEntityListener.class)
@Data
@ToString
public class DroneModel implements Serializable {


	
	private static final long serialVersionUID = -5900228113588522938L;

	@Id
	@Column(name = "drone_model_id")
	private Long droneModelId;

	@Column(name = "drone_model_caption")
	private String droneModelCaption;

	@Column(name = "drone_model_title")
	private String droneModelTitle;

}