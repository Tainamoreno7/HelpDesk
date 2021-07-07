package com.tainamoreno.api.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tainamoreno.api.entity.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String>{
	
	//Filtrar pelo Id e ordenar pela data
	Page<Ticket>findByUserIdOrderByDateDesc(Pageable pages, String userId);
	
	Page<Ticket>findByTitleIgnoreCaseContainingAndStatusIgnoreCaseContainingAndPriorityIgnoreCaseContainingOrderByDateDesc(
			String title, String status, String priority, Pageable pages);
	
	//Filtro para usuário pesquisar seu tickets
	Page<Ticket>findByTitleIgnoreCaseContainingAndStatusIgnoreCaseContainingAndPriorityIgnoreCaseContainingAndUserIdOrderByDateDesc(
			String title, String status, String priority, Pageable pages);
	
	//Filtro para usuário pesquisar seu tickets
	Page<Ticket>findByTitleIgnoreCaseContainingAndStatusIgnoreCaseContainingAndPriorityIgnoreCaseContainingAndAssignedUserIdOrderByDateDesc(
				String title, String status, String priority, Pageable pages);
	
	//Filtrar pelo numero	
	Page<Ticket> findByNumber(Integer number, Pageable pages);
	
		
}
