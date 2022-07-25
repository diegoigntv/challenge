package com.challenge.service;

import org.springframework.stereotype.Service;

import com.challenge.dto.LogInResponseDto;
import com.challenge.dto.SignUpDto;
import com.challenge.dto.SignUpResponseDto;

@Service
public interface IUserService {

	SignUpResponseDto signUp(SignUpDto userData) throws Exception;

	LogInResponseDto logIn() throws Exception;
}
