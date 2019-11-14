package com.expense.expensemanager.utils;

import com.expense.expensemanager.model.Transaction;
import com.expense.expensemanager.payload.ExpenseResponse;

public class ModelMapper {
	
	public static ExpenseResponse mapTransactionToExpense(Transaction transaction) {
		ExpenseResponse expenseResponse = new ExpenseResponse();
		expenseResponse.setTransactionId(transaction.getTransactionid());
		expenseResponse.setAmount(transaction.getTransactionAmount());
		expenseResponse.setDate(transaction.getDateOfTransaction());
		expenseResponse.setMode(transaction.getTransactionMode());
		expenseResponse.setNotes(transaction.getNotes());
		expenseResponse.setCategory(transaction.getCategories().getCategoryName());
		return expenseResponse;
	}
}
