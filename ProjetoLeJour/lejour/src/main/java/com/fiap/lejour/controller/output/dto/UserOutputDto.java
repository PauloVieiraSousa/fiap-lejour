package com.fiap.lejour.controller.output.dto;

import java.time.Instant;

import com.fiap.lejour.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOutputDto {

	private Long id;
	private String nome;
	private String email;
	
	public UserOutputDto(User usuario) {
		this.nome = usuario.getNome();
		this.id = usuario.getId();
		this.email = usuario.getEmail();
	}
	
}
