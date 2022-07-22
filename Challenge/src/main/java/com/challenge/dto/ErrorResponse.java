package com.challenge.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
	/*
	 * public ErrorResponse(String message, List<String> details) { super(); /*
	 * this.message = message; this.details = details;
	 */
	// }

	public ErrorResponse(HttpStatus badRequest, String detail) {
		super();
		
		this.detail = detail;
		this.codigo = badRequest.value();
	}
		
    
	private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
	private int codigo;
	private String detail;

}
