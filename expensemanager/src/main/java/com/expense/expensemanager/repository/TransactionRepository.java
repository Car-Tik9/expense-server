package com.expense.expensemanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.expense.expensemanager.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> , JpaSpecificationExecutor<Transaction>{
	Optional<Transaction>  findBytransactionid(Long transactionId);

	Page<Transaction> findByUserId(Long id, Pageable pageable);
	Page<Transaction> findByUserId(Specification<Transaction> spec,Long id, Pageable pageable);
	Page<Transaction> findAll(Specification<Transaction> spec,Pageable pageable);
}
