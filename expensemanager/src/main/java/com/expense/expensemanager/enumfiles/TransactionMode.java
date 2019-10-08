package com.expense.expensemanager.enumfiles;

public enum TransactionMode {
	IMPS(1),
	NETBANKING(2),
	UPI(3),
	NEFT(4),
	CASH(5),
	OTHER(6);

	private int transactionModeValue;
	
	private TransactionMode(int value) {
		this.transactionModeValue = value;
	}
	
	public static TransactionMode fromMode(int transactionMode) {
		for(TransactionMode transactionModeEnum : TransactionMode.values()) {
			if( transactionMode == transactionModeEnum.transactionModeValue) {
				return transactionModeEnum;
			}
		}
		return null;
	}
}
