package com.fiap.lejour.controller.output.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationTokenOutputDto {
	
	private String tokenType;
	private String token;
	
	public AuthenticationTokenOutputDto(String tokenType, String token) {
		this.tokenType = tokenType;
		this.token = token;
	}
	
	
}
