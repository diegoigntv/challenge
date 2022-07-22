package com.challenge.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.dto.SignUpDto;
import com.challenge.dto.SignUpResponseDto;
import com.challenge.exceptions.EmailNotValidException;
import com.challenge.exceptions.UserAlreadyExistsException;
import com.challenge.model.User;
import com.challenge.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	private String regexPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

	
	public SignUpResponseDto signUp(SignUpDto userData) throws Exception {
		if (!patternMatches(userData.getEmail(), regexPattern)) {
			throw new EmailNotValidException("Email not valid");
		}
		if (userRepository.findByEmail(userData.getEmail()).isPresent())
			throw new UserAlreadyExistsException("User already exist");
			
		
		boolean resp = false;
		User user = new User();
		user.setName(userData.getName());
		user.setEmail(userData.getEmail());
		user.setActive(true);
		user.setCreated(LocalDateTime.now());
		user.setLastLogin(Timestamp.valueOf(LocalDateTime.now()));
		user.setPassword(userData.getPassword());
		user.setPhone(userData.getPhone().getCountrycode() + userData.getPhone().getCitycode()
				+ userData.getPhone().getNumber());

		userRepository.save(user);

		SignUpResponseDto signUpResponse = new SignUpResponseDto();
		signUpResponse.setActive(user.isActive());
		signUpResponse.setCreated(user.getCreated());
		signUpResponse.setId(user.getUserId());
		signUpResponse.setLastLogin(user.getLastLogin());
		signUpResponse.setToken("OK");

		return signUpResponse;

	}


	public static boolean patternMatches(String emailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}


	@Override
	public User findByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
