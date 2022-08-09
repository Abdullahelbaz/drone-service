package com.musala.droneservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.musala.droneservice.model.TransactionsHistory;

public interface TransactionHistoryRepository
		extends PagingAndSortingRepository<TransactionsHistory, Long>, JpaRepository<TransactionsHistory, Long> {

}
