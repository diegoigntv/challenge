package com.challenge.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.challenge.config.FeignClientInterceptor;
import com.challenge.dto.LogInResponseDto;
import com.challenge.dto.Phone;
import com.challenge.dto.SignUpDto;
import com.challenge.dto.SignUpResponseDto;
import com.challenge.exceptions.EmailNotValidException;
import com.challenge.exceptions.PassNotValidException;
import com.challenge.exceptions.UserAlreadyExistsException;
import com.challenge.exceptions.UserNotExistsException;
import com.challenge.model.User;
import com.challenge.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FeignClientInterceptor feignClient;

	
	com.challenge.model.User user;

	private String emailRegexPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	private String passRegexPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,12}$";

	public SignUpResponseDto signUp(SignUpDto userData) throws Exception {
	
		if (!patternMatches(userData.getEmail(), emailRegexPattern)) {
			throw new EmailNotValidException("Email not valid");
		}
		if (userRepository.findByEmail(userData.getEmail()).isPresent())
			throw new UserAlreadyExistsException("User already exist");
		
		if (!patternMatches(userData.getPassword(), passRegexPattern))
			throw new PassNotValidException("Password not valid");


		boolean resp = false;
		User user = new User();
		user.setName(userData.getName());
		user.setEmail(userData.getEmail());
		user.setActive(true);
		user.setCreated(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		user.setPassword(userData.getPassword());
		user.setPhone(String.join(",", userData.getPhone().getCountrycode(),
				String.valueOf(userData.getPhone().getCitycode()), String.valueOf(userData.getPhone().getNumber()))); //JAVA 8 - JOIN
		
		userRepository.save(user);
		
		SignUpResponseDto signUpResponse = new SignUpResponseDto();
		signUpResponse.setActive(user.isActive());
		signUpResponse.setCreated(user.getCreated());
		signUpResponse.setId(user.getUserId());
		signUpResponse.setLastLogin(user.getLastLogin());
		signUpResponse.setToken(createAuthToken(user.getEmail()));

		return signUpResponse;

	}

	public static boolean patternMatches(String emailAddress, String regexPattern) {
		return Pattern.compile(regexPattern).matcher(emailAddress).matches();
	}

	public User findByEmail(String email) throws Exception {

		Optional<User> userSearched = userRepository.findByEmail(email);  // OPTIONAL - Java 8
		if (!userSearched.isPresent()) {
			throw new UserNotExistsException("The user doesn't exist");
		}
		return userSearched.get();
	}

	public LogInResponseDto logIn() throws Exception {
		LogInResponseDto logInResponse = new LogInResponseDto();
		User userSearched = findByEmail(decodeToken());
		
		logInResponse.setActive(userSearched.isActive());
		logInResponse.setCreated(userSearched.getCreated());
		logInResponse.setEmail(userSearched.getEmail());
		logInResponse.setId(userSearched.getUserId());
		logInResponse.setLastLogin(LocalDateTime.now());
		logInResponse.setToken(createAuthToken(userSearched.getEmail()));
		logInResponse.setName(userSearched.getName());
		logInResponse.setPassword(userSearched.getPassword());
		
		String phoneSearched = userSearched.getPhone();
		Stream<String> streamPhone = Arrays.stream(phoneSearched.split( "," )); // STREAM - Java 8
		List<String> phoneList = streamPhone.collect(Collectors.toList());
		Phone phone = new Phone();
		phone.setCitycode(Integer.parseInt(phoneList.get(1)));
		phone.setCountrycode(phoneList.get(0));
		phone.setNumber(Long.parseLong(phoneList.get(2)));
		logInResponse.setPhone(phone);
		return logInResponse;
	}

	public String decodeToken() throws Exception {
		String token = feignClient.getBearerTokenHeader();
		String[] separated = token.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		String header = new String(decoder.decode(separated[0]));
		String payload = new String(decoder.decode(separated[1]));
		String[] decod = payload.split("\"");
		String email = decod[7];
		return email;
	}

	public String createAuthToken(String email) throws Exception {

		String secretKey = "ch4ll3ng3";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		return Jwts.builder().setId("chl").setSubject(email)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

	}

}
