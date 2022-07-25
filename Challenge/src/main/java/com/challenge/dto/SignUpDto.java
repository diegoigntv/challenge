package com.challenge.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

	private String name;

	@NotNull
	private String email;

	@NotNull
	private String password;

	private Phone phone;

}
