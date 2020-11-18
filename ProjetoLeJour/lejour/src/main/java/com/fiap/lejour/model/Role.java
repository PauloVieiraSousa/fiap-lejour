package com.fiap.lejour.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{

	public static final Role ROLE_ADMIN = new Role("ROLE_ADMIN");
	
	@Id
	private String authority;
	
	/**
	 * @deprecated
	 * */
	public Role() {}
	
	public Role(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}
	
}
