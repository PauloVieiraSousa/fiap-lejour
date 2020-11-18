package com.fiap.lejour.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fiap.lejour.security.service.UserService;


public class JwtAuthenticationFilter extends OncePerRequestFilter {


	private TokenManager tokenManager;
	private UserService userService;
	
	public JwtAuthenticationFilter(TokenManager tokenManager, UserService usuarioService) {
		this.userService = usuarioService;
		this.tokenManager = tokenManager;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			String jwt = getTokenFromRequest(request);
			
			if (tokenManager.isValid(jwt)) {
				Long id = tokenManager.getUserIdFromToken(jwt);
				UserDetails userDetails = userService.loadUserById(id);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			filterChain.doFilter(request, response);
	}
	
	
	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
	
	
	
}
