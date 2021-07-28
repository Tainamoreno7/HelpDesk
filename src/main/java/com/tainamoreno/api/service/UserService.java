package com.tainamoreno.api.service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.tainamoreno.api.security.entity.User;

public interface UserService {
	
	User findByEmail(String email);
	
	User createOrUpdate(User user);
	
	Optional<User> findById(String id);
	
	void delete(String id);
	
	Page<User> findAll(int page, int count);

}
