package com.expense.expensemanager.payload;

import java.util.List;
import java.util.Map;

public class PagedRequest {
	private int page;
	
	private int size;
	
	private Map<String, Object> filterData;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Map<String, Object> getFilterData() {
		return filterData;
	}

	public void setFilterData(Map<String, Object> filterData) {
		this.filterData = filterData;
	}
	
}
