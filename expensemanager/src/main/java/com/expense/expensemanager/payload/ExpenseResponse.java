package com.expense.expensemanager.payload;

import java.util.List;
import java.util.Date;

import com.expense.expensemanager.model.Transaction;

public class ExpenseResponse {
	
	private long transactionId;
	
	private long id;
	
	private Double amount;
	
	private String notes;
	
	private String category;
	
	private Date date;
	
	private String mode;

	private int cdDiv;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getCdDiv() {
		return cdDiv;
	}

	public void setCdDiv(int i) {
		this.cdDiv = i;
	}
	
} 
