package com.challenge.test;

import java.time.LocalDateTime;

import org.mockito.InjectMocks;

import com.challenge.dto.SignUpResponseDto;
import com.challenge.service.UserServiceImpl;

public class SignUpFixture {

	@InjectMocks
	private UserServiceImpl service;
	public static SignUpResponseDto getSignUp() throws Exception {
		/*
		Phone phone = new Phone(998565673, 9, "+56");
		SignUpDto signUp = new SignUpDto("Diego", "Diego@Gmail.com", "AbcdeF12", phone);
		return signUp;
		*/
		SignUpResponseDto responseExpected = new SignUpResponseDto();
		responseExpected.setActive(true);
		responseExpected.setCreated(LocalDateTime.now());
	//	responseExpected.setId("1");
		
		responseExpected.setLastLogin(LocalDateTime.now());
		return responseExpected;
		
	}
}
