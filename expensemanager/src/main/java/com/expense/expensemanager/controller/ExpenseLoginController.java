package com.expense.expensemanager.controller;


import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.expense.expensemanager.model.User;
import com.expense.expensemanager.payload.ApiResponse;
import com.expense.expensemanager.payload.JwtAuthenticationResponse;
import com.expense.expensemanager.payload.LoginRequest;
import com.expense.expensemanager.payload.SignUpRequest;
import com.expense.expensemanager.payload.UserProfile;
import com.expense.expensemanager.repository.UserRepository;
import com.expense.expensemanager.security.CurrentUser;
import com.expense.expensemanager.security.JwtTokenProvider;
import com.expense.expensemanager.security.UserPrincipal;
import com.expense.expensemanager.service.ExpenseService;

@RestController
@RequestMapping("/expense/auth")
public class ExpenseLoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	ExpenseService expenseService;

	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUserNameOrEmail(),
						loginRequest.getPassword()
						)
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}



	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if(userRepository.existsByUsername(signUpRequest.getUserName())) {
			return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if(userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUserName(),
				signUpRequest.getEmail(), signUpRequest.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User result = userRepository.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(result.getUsername()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}
	
	
}
