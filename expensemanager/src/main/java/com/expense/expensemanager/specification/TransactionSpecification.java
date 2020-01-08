package com.expense.expensemanager.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.expense.expensemanager.model.Categories;
import com.expense.expensemanager.model.Transaction;

public class TransactionSpecification implements Specification<Transaction> {
	private Map<String, Object> filterDataMap;
	public TransactionSpecification(Map<String, Object> filterDataMap) {
		this.filterDataMap = filterDataMap;
	}
	@Override
	public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();
		if(filterDataMap.containsKey("transactionTypeList")) {
			List<String> transactionTypeList = (List<String>) filterDataMap.get("transactionTypeList");
			if(!transactionTypeList.isEmpty()) {
				predicates.add(root.get("transactionMode").in(transactionTypeList));
			}
		}
		if(filterDataMap.containsKey("amount") && !StringUtils.isEmpty(filterDataMap.get("amount"))){
			predicates.add(criteriaBuilder.equal(root.get("transactionAmount"), Long.valueOf(filterDataMap.get("amount").toString())));
		}
		if(filterDataMap.containsKey("transactionDate")) {
			try {
				Date transactionDate = new SimpleDateFormat("dd/MM/yyyy").parse(filterDataMap.get("transactionDate").toString());
				predicates.add(criteriaBuilder.equal(root.get("dateOfTransaction"), transactionDate));
			}catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(filterDataMap.containsKey("categoryTypeList")) {
			List<String> categoryTypeList = (List<String>) filterDataMap.get("categoryTypeList");
			if(!categoryTypeList.isEmpty()) {
				Join<Transaction, Categories> join = root.join("categories");
				predicates.add(join.get("categoryName").in(categoryTypeList));
			}
		}
		if(filterDataMap.containsKey("cdList")) {
			List<String> creditDebitList = (List<String>) filterDataMap.get("cdList");
			if(!creditDebitList.isEmpty()) {
				predicates.add(root.get("cdDiv").in(creditDebitList));
			}
		}
		return andTogether(predicates, criteriaBuilder);
	}
	
	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

}
