package com.expense.expensemanager.controller;

import java.util.Arrays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expense.expensemanager.model.Transaction;
import com.expense.expensemanager.payload.ApiResponse;
import com.expense.expensemanager.payload.ExpenseRequest;
import com.expense.expensemanager.payload.ExpenseResponse;
import com.expense.expensemanager.payload.PagedRequest;
import com.expense.expensemanager.payload.PagedResponse;
import com.expense.expensemanager.repository.TransactionRepository;
import com.expense.expensemanager.security.CurrentUser;
import com.expense.expensemanager.security.UserPrincipal;
import com.expense.expensemanager.service.ExpenseService;

@RestController
@RequestMapping("/expense")
public class TransactionController {
	
	@Autowired
	ExpenseService expenseService;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@PostMapping("/saveexpense")
	public ResponseEntity<?> createExpense(@Valid @RequestBody ExpenseRequest expenseRequest){
		expenseService.createExpense(expenseRequest);
		return ResponseEntity.ok(new ApiResponse(true, "Expense Saved Successfullly"));
	}
	
	@PostMapping("/getExpenses")
	public PagedResponse<ExpenseResponse> getExpenses(@CurrentUser UserPrincipal currentUser , @RequestBody PagedRequest pagedRequest){
		return expenseService.getExpenses(currentUser,pagedRequest.getPage(),pagedRequest.getSize());
	}
}
