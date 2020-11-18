package com.fiap.lejour.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fiap.lejour.controller.input.dto.UserInputDto;
import com.fiap.lejour.controller.output.dto.UserOutputDto;
import com.fiap.lejour.model.User;
import com.fiap.lejour.repository.UserRepository;

@RequestMapping("/api/usuario")
@RestController()
public class UsuarioController {

	@Autowired
	private UserRepository usuarioRepository;
	
	@ResponseBody
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserOutputDto> createUsuario(@RequestBody UserInputDto usuarioDto, UriComponentsBuilder uriBuilder){
		User user = usuarioDto.build();
		
		this.usuarioRepository.save(user);
		
		URI path = uriBuilder.path("/api/usuario/{id}")
				.buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(path).body(new UserOutputDto(user));
	}
	
	
}
