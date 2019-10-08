package com.expense.expensemanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.expensemanager.payload.ApiResponse;
import com.expense.expensemanager.payload.ExpenseRequest;
import com.expense.expensemanager.service.ExpenseService;

@RestController
@RequestMapping("/expense")
public class TransactionController {
	
	@Autowired
	ExpenseService expenseService;
	
	@PostMapping("/saveexpense")
	public ResponseEntity<?> createExpense(@Valid @RequestBody ExpenseRequest expenseRequest){
		expenseService.createExpense(expenseRequest);
		return ResponseEntity.ok(new ApiResponse(true, "Expense Saved Successfullly"));
	}
}
