package com.expense.expensemanager.payload;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ExpenseRequest {
	
	@NotBlank
	private String dateOfTransaction;
	
	@NotNull
	private Double transactionAmount;
	
	@NotNull
	private int transactionMode;
	
	@NotNull
	private int cdDiv;
	
	@NotNull
	private int categoryId;
	
	private String moneySendto;
	
	private String notes;
	
	public String getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(String dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public int getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(int transactionMode) {
		this.transactionMode = transactionMode;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getCdDiv() {
		return cdDiv;
	}

	public void setCdDiv(int cdDiv) {
		this.cdDiv = cdDiv;
	}

	public String getMoneySendto() {
		return moneySendto;
	}

	public void setMoneySendto(String moneySendto) {
		this.moneySendto = moneySendto;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
