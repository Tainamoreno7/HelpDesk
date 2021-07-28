package com.tainamoreno.api.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tainamoreno.api.security.entity.User;
import com.tainamoreno.api.security.jwt.JwtAuthenticationRequest;
import com.tainamoreno.api.security.jwt.JwtTokenUtil;
import com.tainamoreno.api.security.model.CurrentUser;
import com.tainamoreno.api.service.UserService;

@RestController
@CrossOrigin(origins ="*") //permitir acesso qualquer porta(todas as portas) a API
public class AuthenticationRestController {

	//injestão de dependências
		
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	
	//método que será chamado para criar
	
	@PostMapping(value="/api/auth") //solicitar um token
	public ResponseEntity<?> createAuthenticaticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authenticationRequest.getEmail(),
						authenticationRequest.getPassword()
						)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		final User user = userService.findByEmail(authenticationRequest.getEmail());
		user.setPassword(null);
		return ResponseEntity.ok(new CurrentUser(token, user));
	}
	
	@PostMapping(value="/api/refresh") //atualizar o token
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request){
		String token = request.getHeader("Authorization");
		String username = jwtTokenUtil.getUsernameFromToken(token);
		final User user = userService.findByEmail(username);
		
		if (jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshedToken =jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new CurrentUser(refreshedToken, user));
		}else {
			return ResponseEntity.badRequest().body(null);
		}
	
	}
	
}
