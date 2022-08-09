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
@Table(name = "drone_state")
@EntityListeners(AuditingEntityListener.class)
@Data
@ToString
public class DroneState implements Serializable {

	
	private static final long serialVersionUID = -824334636582359389L;

	public static final String STATE_IDLE_TITLE = "IDLE";

	public static final String STATE_LOADING_TITLE = "LOADING";
	
	@Id
	@Column(name = "drone_state_id")
	private Long droneModelId;

	@Column(name = "drone_state_caption")
	private String droneModelCaption;

	@Column(name = "drone_state_title")
	private String droneModelTitle;

}