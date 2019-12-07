package com.expense.expensemanager.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.expense.expensemanager.model.Transaction;
import com.expense.expensemanager.model.User;
import com.expense.expensemanager.payload.ExpenseResponse;
import com.expense.expensemanager.payload.UserProfile;

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
	
	public static UserProfile mapUserToUserProfile(User user) {
		 
		UserProfile userProfile = new UserProfile();
		userProfile.setEmailId(user.getEmail());
		userProfile.setName(user.getName());
		userProfile.setImageUrl(ExpenseUtils.formProfilePictureURL(user.getFilePath()));
		userProfile.setImageAltText(user.getFileName());
		return userProfile;
	}
}
