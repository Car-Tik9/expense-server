package com.expense.expensemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ExpenseException extends RuntimeException{
	public ExpenseException(String message) {
		super(message);
	}
	
	public ExpenseException(String message, Throwable cause) {
        super(message, cause);
    }
}
