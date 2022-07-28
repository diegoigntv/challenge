package com.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.dto.LogInResponseDto;
import com.challenge.dto.SignUpDto;
import com.challenge.dto.SignUpResponseDto;
import com.challenge.service.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping(path = "/sign-up")
	public ResponseEntity<SignUpResponseDto> signUpUser(@RequestBody SignUpDto userData) throws Exception {

		return ResponseEntity.ok(userServiceImpl.signUp(userData));
	}

	@GetMapping(path = "/log-in")
	public ResponseEntity<LogInResponseDto> loginUser() throws Exception {
		return ResponseEntity.ok(userServiceImpl.logIn());
	}

}
