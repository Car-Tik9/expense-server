package com.expense.expensemanager.enumfiles;

public enum TransactionType {
	CREDIT(0),
	DEBIT(1);
	
	private int creditDebitDiv;
	
	private TransactionType(int creditDebitDiv) {
		this.creditDebitDiv  = creditDebitDiv;
	}
	
	public static TransactionType fromDebitdiv(int value) throws Exception {
		for(TransactionType transactionType : TransactionType.values()) {
			if(value == transactionType.creditDebitDiv) {
				return transactionType;
			}
		}
		return null;
	}
}
