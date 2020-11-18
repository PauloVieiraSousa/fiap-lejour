package com.fiap.lejour.security.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fiap.lejour.model.User;
import com.fiap.lejour.repository.UserRepository;



@Service
public class UserService implements UserDetailsService {
	
	
	private UserRepository userRepository;
	
	UserService(UserRepository usuarioRepository){
		this.userRepository = usuarioRepository;
	}
	

	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		Optional<User> possibleUser = userRepository.findByEmail(username);
		return possibleUser
				.orElseThrow(() -> 
				new UsernameNotFoundException("Não foi possível autenticar"));
		
	}
	
	
	public UserDetails loadUserById (Long userId) {
		Optional<User> possibleUser = userRepository.findById(userId);
		return possibleUser.orElseThrow(() -> new UsernameNotFoundException("Não foi possível autenticar"));
	}

}
