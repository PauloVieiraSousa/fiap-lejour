package com.fiap.lejour.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.lejour.controller.input.dto.LoginInputDto;
import com.fiap.lejour.controller.output.dto.AuthenticationTokenOutputDto;
import com.fiap.lejour.security.jwt.TokenManager;


@RestController
@RequestMapping("/api/auth")
public class UserAuthenticationController {
	
	private AuthenticationManager authManager;
	private TokenManager tokenManager;
	
	
	public UserAuthenticationController(AuthenticationManager authManager, TokenManager tokenManager) {
		this.authManager = authManager;
		this.tokenManager = tokenManager;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationTokenOutputDto> authentication( @RequestBody LoginInputDto loginInfo){
		
		UsernamePasswordAuthenticationToken authenticationToken = loginInfo.build();
		Authentication authentication = authManager.authenticate(authenticationToken);
		String token = tokenManager.generateToken(authentication);
		AuthenticationTokenOutputDto tokenResponse = new AuthenticationTokenOutputDto("Bearer", token);
		
		return ResponseEntity.ok(tokenResponse);
	}
	
}
