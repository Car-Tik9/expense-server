package com.expense.expensemanager.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.expense.expensemanager.enumfiles.CategoriesEnum;
import com.expense.expensemanager.enumfiles.TransactionMode;
import com.expense.expensemanager.exception.BadRequestException;
import com.expense.expensemanager.model.Categories;
import com.expense.expensemanager.model.Transaction;
import com.expense.expensemanager.model.User;
import com.expense.expensemanager.payload.ExpenseRequest;
import com.expense.expensemanager.payload.ExpenseResponse;
import com.expense.expensemanager.payload.PagedResponse;
import com.expense.expensemanager.repository.CategoriesRepository;
import com.expense.expensemanager.repository.TransactionRepository;
import com.expense.expensemanager.repository.UserRepository;
import com.expense.expensemanager.security.UserPrincipal;
import com.expense.expensemanager.utils.ModelMapper;

@Service
public class ExpenseService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	@Value("${files.profilepicpath}")
	private String fileUploadPath;
	
	public void createExpense(ExpenseRequest expenseRequest) {
		Transaction transaction = new Transaction();
		transaction.setCdDiv(expenseRequest.getCdDiv());
		try {
			transaction.setDateOfTransaction(new SimpleDateFormat("dd/MM/yyyy").parse(expenseRequest.getTransactionDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new BadRequestException("Please Enter the Proper date");
		}
		transaction.setMoneySendto(expenseRequest.getMoneySendto());
		transaction.setTransactionAmount(expenseRequest.getTransactionAmount());
		transaction.setNotes(expenseRequest.getNotes());
		transaction.setTransactionModeType(expenseRequest.getTransactionMode());
		transaction.setTransactionMode(TransactionMode.fromMode(expenseRequest.getTransactionMode()).toString());
		transaction.setUser(getCurrentUser());
		transaction.setCategories(
				categoriesRepository.findBycategoryName(
						CategoriesEnum.fromMode(expenseRequest.getCategoryId())
						.toString()).get()
				);
		transactionRepository.save(transaction);
	}

	private User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal(); 
		return userRepository.findById(userPrincipal.getId()).get();
	}

	public PagedResponse<ExpenseResponse> getExpenses(UserPrincipal currentUser, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC,"dateOfTransaction");
		Page<Transaction> expenses = transactionRepository.findByUserId(currentUser.getId(),pageable);
		if ( expenses.getNumberOfElements() == 0) {
			return new PagedResponse<>(Collections.EMPTY_LIST,expenses.getNumber(),expenses.getSize(),expenses.getTotalElements(),
					expenses.getTotalPages(),expenses.isLast());
		}
		List<ExpenseResponse> expenseResponse = expenses.map(converter -> {
			return ModelMapper.mapTransactionToExpense(converter);
		}).getContent();
		return new PagedResponse<>(expenseResponse, expenses.getNumber(), 
				expenses.getSize(), 
				expenses.getTotalElements(), expenses.getTotalPages(), expenses.isLast());
	}
	
	
	public String saveProfilePicture(MultipartFile profilePic) throws IllegalStateException, IOException {
		User user = getCurrentUser();
		String filePath = "";
		File file = new File(fileUploadPath);
		if(!file.exists()) {
			file.mkdir();
		}
		if (file != null) {
			if (!profilePic.getOriginalFilename().equals("")) {
				filePath = fileUploadPath+File.separator+user.getId()+"_"+profilePic.getOriginalFilename();
				profilePic.transferTo(new File(filePath));
				user.setFileName(profilePic.getOriginalFilename());
				user.setFilePath(filePath);
				userRepository.save(user);
			}
		}
		return filePath;
	}
}
