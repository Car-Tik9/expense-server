package com.expense.expensemanager.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.expense.expensemanager.enumfiles.CategoriesEnum;
import com.expense.expensemanager.enumfiles.TransactionMode;
import com.expense.expensemanager.exception.BadRequestException;
import com.expense.expensemanager.model.Categories;
import com.expense.expensemanager.model.Transaction;
import com.expense.expensemanager.model.User;
import com.expense.expensemanager.payload.ExpenseRequest;
import com.expense.expensemanager.repository.CategoriesRepository;
import com.expense.expensemanager.repository.TransactionRepository;
import com.expense.expensemanager.repository.UserRepository;

@Service
public class ExpenseService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	public void createExpense(ExpenseRequest expenseRequest) {
		Transaction transaction = new Transaction();
		transaction.setCdDiv(expenseRequest.getCdDiv());
		try {
			transaction.setDateOfTransaction(new SimpleDateFormat("dd/MM/yyyy").parse(expenseRequest.getDateOfTransaction()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new BadRequestException("Please Enter the Proper date");
		}
		transaction.setMoneySendto(expenseRequest.getMoneySendto());
		transaction.setTransactionAmount(expenseRequest.getTransactionAmount());
		transaction.setNotes(expenseRequest.getNotes());
		transaction.setTransactionModeType(expenseRequest.getTransactionMode());
		transaction.setTransactionMode(TransactionMode.fromMode(expenseRequest.getTransactionMode()).toString());
		transaction.setUser(getCurrentUser());
		transaction.setCategories(
				categoriesRepository.findBycategoryName(
						CategoriesEnum.fromMode(expenseRequest.getCategoryId())
						.toString()).get()
				);
		transactionRepository.save(transaction);
	}

	private User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.findByUsernameOrEmail(auth.getName(),auth.getName()).get();
	}

}
