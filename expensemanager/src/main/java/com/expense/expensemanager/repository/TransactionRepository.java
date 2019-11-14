package com.expense.expensemanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.expensemanager.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	Optional<Transaction>  findBytransactionid(Long transactionId);

	Page<Transaction> findByUserId(Long id, Pageable pageable);
}
