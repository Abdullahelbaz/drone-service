package com.musala.droneservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.validation.annotation.Validated;
import lombok.Data;
import lombok.ToString;

@Data
@Validated
@Entity
@Table(name = "drone_tranasction_type")
@ToString
public class TransactionType {

	public static final Long TRANSACTION_TYPE_FINE_BY_GIVEN_DRONE = 1L;
	public static final Long TRANSACTION_TYPE_REGISTER_DRONE = 2L;
	public static final Long TRANSACTION_TYPE_DRONES_AVAILABLE = 3L;
	public static final Long TRANSACTION_TYPE_LOAD_MEDICATION = 4L;
	public static final Long TRANSACTION_TYPE_CHECK_BATTARY_CAPACITY = 5L;

	@Id
	@Column(name = "transaction_type_id")
	private Long transactionTypeId;

	@Column(name = "transaction_type_name")
	private String transactionTypeName;

}
