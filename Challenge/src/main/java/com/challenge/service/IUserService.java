package com.challenge.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.challenge.dto.SignUpDto;
import com.challenge.dto.SignUpResponseDto;
import com.challenge.exceptions.UserAlreadyExistsException;
import com.challenge.model.User;

@Service
public interface IUserService {

	SignUpResponseDto signUp(SignUpDto userData) throws Exception;
	User findByEmail(String email) throws Exception;

}
