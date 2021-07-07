package com.tainamoreno.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tainamoreno.api.entity.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	User findByEmail(String email);
	

}
