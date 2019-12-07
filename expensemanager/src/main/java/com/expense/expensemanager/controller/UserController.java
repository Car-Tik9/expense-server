package com.expense.expensemanager.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.expense.expensemanager.model.User;
import com.expense.expensemanager.payload.ApiResponse;
import com.expense.expensemanager.payload.FileUploadResponse;
import com.expense.expensemanager.payload.UserProfile;
import com.expense.expensemanager.repository.UserRepository;
import com.expense.expensemanager.security.CurrentUser;
import com.expense.expensemanager.security.UserPrincipal;
import com.expense.expensemanager.service.ExpenseService;
import com.expense.expensemanager.utils.ExpenseUtils;
import com.expense.expensemanager.utils.ModelMapper;
@RestController
@RequestMapping("/expense/user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ExpenseService expenseService;
	
	public User getUserProfile(@PathVariable(value = "username") String username) {
		return userRepository.findByUsername(username).get();
	}
	
	@PostMapping("/getUserProfile")
	public UserProfile getUserProfile(@CurrentUser UserPrincipal currentUser) {
		User user = userRepository.findByUsername(currentUser.getUsername()).get();
		return ModelMapper.mapUserToUserProfile(user);
	}
	
	@PostMapping(value="/uploadProfilePicture",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		String filePath = expenseService.saveProfilePicture(file);
		return ResponseEntity.ok(new FileUploadResponse(true, "Profile Picture Uploaded sucessfully",ExpenseUtils.formProfilePictureURL(filePath)));
	}
} 
