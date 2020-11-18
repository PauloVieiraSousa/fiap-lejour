package com.fiap.lejour.controller.input.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fiap.lejour.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInputDto {

	private String nome;
	private String password;
	private String email;

	public User build() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(this.password);
		return new User(this.nome, encodedPassword , this.email);
	}
	
	
}
