package com.musala.droneservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.ToString;

@Data
@Validated
@Entity
@Table(name = "drone_transaction_history")
@ToString
public class TransactionsHistory {

	@Id
	@SequenceGenerator(name = "drone_transaction_history_seq_gen", sequenceName = "drone_transaction_his_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drone_transaction_history_seq_gen")
	@Column(name = "drone_transaction_id")
	private Long transactionHistoryId;

	@Column(name = "transaction_date")
	private LocalDateTime transactionDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "transaction_type_id")
	private TransactionType transactionType;

	@Column(name = "request_details")
	private String requestDetails;

	@Column(name = "drone_details")
	private String droneDetails;

}
