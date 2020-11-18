package com.fiap.lejour.controller.output.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorOutputDto {
	
	private String code;
	private String message;
	
	
	public ErrorOutputDto(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
