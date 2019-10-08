package com.expense.expensemanager.enumfiles;

public enum CategoriesEnum {
	FOOD(1),
	SHOPPING(2),
	TRAVEL(3),
	ENTERTAINMENT(4),
	MEDICAL(5),
	OTHER(6);

	private int categoryId;
	
	private CategoriesEnum(int value) {
		this.categoryId = value;
	}
	
	public static CategoriesEnum fromMode(int transactionMode) {
		for(CategoriesEnum category : CategoriesEnum.values()) {
			if( transactionMode == category.categoryId) {
				return category;
			}
		}
		return null;
	}
}
