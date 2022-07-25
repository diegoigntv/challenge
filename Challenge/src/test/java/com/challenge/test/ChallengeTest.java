package com.challenge.test;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.dto.Phone;
import com.challenge.dto.SignUpDto;
import com.challenge.dto.SignUpResponseDto;
import com.challenge.service.IUserService;

class ChallengeTest {

	@Autowired
	private IUserService userService;

	@Test
	void signUp() throws Exception {
		Phone phone = new Phone(998565673, 9, "+56");
		SignUpDto signUp = new SignUpDto("Diego", "Diego@Gmail.com", "AbcdeF12", phone);
		SignUpResponseDto responseExpected = new SignUpResponseDto();
		responseExpected.setActive(true);
		responseExpected.setCreated(LocalDateTime.now());
		responseExpected.setId("1");
		responseExpected.setLastLogin(LocalDateTime.now());
		responseExpected.setToken(
				"eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJjaGwiLCJzdWIiOiJkaWVnb0BzYWFzZGFzZHNhZHNhYWxhaXZlLmNvbSIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2NTg3MzM1MDEsImV4cCI6MTY1ODczNDEwMX0.se0BPNmpK2yvPqgMPoYne4_UIQ-gv_yqGykJ17weoMsv7cWFOVMdNlggmqRcIToe2aDR79ekusbaX42rBcBBGQ");

		System.out.println(signUp);
		SignUpResponseDto resp = userService.signUp(signUp);
		Assert.assertEquals(responseExpected,resp );
	}

}
