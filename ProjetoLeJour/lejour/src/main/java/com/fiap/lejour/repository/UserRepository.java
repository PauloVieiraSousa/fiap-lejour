package com.fiap.lejour.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.fiap.lejour.model.User;



public interface UserRepository extends Repository<User, Long>{
	
	public User save(User usuario);
	
	public Optional<User> findByEmail(String email);
	
	public Optional<User> findById(Long id);

	
}
