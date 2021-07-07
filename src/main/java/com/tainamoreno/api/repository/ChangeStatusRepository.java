package com.tainamoreno.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tainamoreno.api.entity.ChangeStatus;

public interface ChangeStatusRepository extends MongoRepository<ChangeStatus, String>{
	
	//Alteração de todos os tickets
	
	Iterable<ChangeStatus> FindByTicketIdOrderByDateChangeStatusDesc(String ticketId);
	

}
