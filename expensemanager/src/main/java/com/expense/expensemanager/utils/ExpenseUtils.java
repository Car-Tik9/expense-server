package com.expense.expensemanager.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.expense.expensemanager.model.User;
import com.expense.expensemanager.repository.UserRepository;

public class ExpenseUtils {
	
	@Autowired
	UserRepository userRepository;
	
	public  User getCurrentUser() {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			 return userRepository.findByUsernameOrEmail("cartik","cartik").get();
	}
	
	public static String formProfilePictureURL(String filePath) {
		String fileLoaction = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/images/{filename}")
				.buildAndExpand(FilenameUtils.getName(filePath)).toUriString();
		return fileLoaction;
	}
	
}
