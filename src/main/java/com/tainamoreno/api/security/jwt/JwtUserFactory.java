package com.tainamoreno.api.security.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.tainamoreno.api.security.entity.User;
import com.tainamoreno.api.security.enums.ProfileEnum;

public class JwtUserFactory {

	private JwtUserFactory() {
		
	}
	//Converte/gera JwtUser com base nos dados de usuário
	public static JwtUser create(User user) {
		return new JwtUser(user.getId(), user.getEmail(), user.getPassword(),
				mapToGrantedAuthorities(user.getProfile()));
	}
	//converte o perfil de usuário para o formato utilizado pelo springsecurity
	private static List<GrantedAuthority>mapToGrantedAuthorities(ProfileEnum profileEnum){
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(profileEnum.toString()));
			return authorities;
			
	}
	
}
