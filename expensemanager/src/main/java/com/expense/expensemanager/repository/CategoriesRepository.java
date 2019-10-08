package com.expense.expensemanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.expensemanager.model.Categories;
import com.expense.expensemanager.model.Transaction;
import com.expense.expensemanager.model.User;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
	
	Optional<Categories>  findBycategoryName(String categoryName);
}
