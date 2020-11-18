package com.fiap.lejour.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Entity
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	private String nome;
	private String password;
	
	private Instant creationInstant = Instant.now();
	private Instant lastUpdate = Instant.now();
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Role> authorities = new ArrayList<>();
	
	/**
	 * @deprecated
	 * */
	public User() {}

	public User(String nome, String password, String email) {
		this.nome = nome;
		this.password = password;
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public Long getId() {
		return this.id;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(email, user.email);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

}
