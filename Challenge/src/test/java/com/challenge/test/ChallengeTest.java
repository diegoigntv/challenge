package com.challenge.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.dto.Phone;
import com.challenge.dto.SignUpDto;
import com.challenge.dto.SignUpResponseDto;
import com.challenge.repository.UserRepository;
import com.challenge.service.UserServiceImpl;

import junit.framework.Assert;

@ExtendWith(MockitoExtension.class)
class ChallengeTest {

	@InjectMocks
	private UserServiceImpl service;
	
	@Mock
	private UserRepository repository;

	@SuppressWarnings("deprecation")
	@Test
	void signUp() throws Exception {
		Phone phone = new Phone(998565673, 9, "+56");
		SignUpDto signUp = new SignUpDto("Diego", "Diego@Gmail.com", "AbcdoeF12", phone);
		
		SignUpResponseDto expectedResponse = SignUpFixture.getSignUp();
		expectedResponse.setToken((service.createAuthToken("Diego@Gmail.com")));
		SignUpResponseDto response = service.signUp(signUp);
		System.out.println(expectedResponse);
		System.out.println(response);
		Assert.assertEquals(expectedResponse, response);
		
		
	}
	/*
	@Test
	void signUp() throws Exception {
		Phone phone = new Phone(998565673, 9, "+56");
		SignUpDto signUp = new SignUpDto("Diego", "Diego@Gmail.com", "AbcdoeF12", phone);
		
		SignUpResponseDto expectedResponse = SignUpFixture.getSignUp();
		expectedResponse.setToken((service.createAuthToken("Diego@Gmail.com")));
		SignUpResponseDto response = service.signUp(signUp);
		System.out.println(expectedResponse);
		System.out.println(response);
		Assert.assertEquals(expectedResponse, response);
		
		
*/

}
