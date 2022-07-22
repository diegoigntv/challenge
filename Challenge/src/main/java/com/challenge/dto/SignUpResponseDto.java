package com.challenge.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDto {
	private String id;
	private LocalDateTime created;
	private Timestamp lastLogin;
	private String token;
	private boolean isActive;
}
