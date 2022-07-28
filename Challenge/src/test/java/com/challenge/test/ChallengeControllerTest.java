package com.challenge.test;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.controller.UserController;
import com.challenge.dto.Phone;
import com.challenge.dto.SignUpDto;
import com.challenge.dto.SignUpResponseDto;
import com.challenge.service.UserServiceImpl;

import junit.framework.Assert;

import org.springframework.http.ResponseEntity;


@ExtendWith(MockitoExtension.class)
public class ChallengeControllerTest {
	@InjectMocks
	private UserController controller;
	
	@Mock
	private UserServiceImpl service;
	
	@SuppressWarnings("deprecation")
	@Test
	void signUp() throws Exception {
		System.out.println("ACA");

		Phone phone = new Phone(998565673, 9, "+56");
		SignUpDto signUp = new SignUpDto("Diego", "Diego@Gmail.com", "AbcdoeF12", phone);
		SignUpResponseDto excpectedResponse = SignUpFixture.getSignUp();
		when(service.signUp(signUp)).thenReturn(excpectedResponse);
		ResponseEntity<SignUpResponseDto> actualSignUp = controller.signUpUser(signUp);
		System.out.println(actualSignUp);
		System.out.println(excpectedResponse);
		Assert.assertEquals(excpectedResponse, actualSignUp.getBody());
		
	}
	

}
