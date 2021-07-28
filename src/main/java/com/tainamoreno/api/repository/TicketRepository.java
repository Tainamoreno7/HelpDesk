package com.tainamoreno.api.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tainamoreno.api.security.entity.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String>{
	
	//Filtrar pelo Id e ordenar pela data
	Page<Ticket>findByUserIdOrderByDateDesc(Pageable pages, String userId);
	
	Page<Ticket>findByTitleIgnoreCaseContainingAndStatusAndPriorityOrderByDateDesc(
			String title, String status, String priority, Pageable pages);
	
	//Filtro para usuário pesquisar seu tickets
	//containing equivale ao like do sql
	Page<Ticket>findByTitleIgnoreCaseContainingAndStatusAndPriorityAndUserIdOrderByDateDesc(
			String title, String status, String priority, Pageable pages);
	
	//Filtro para usuário pesquisar seu tickets
	Page<Ticket>findByTitleIgnoreCaseContainingAndStatusAndPriorityAndAssignedUserIdOrderByDateDesc(
				String title, String status, String priority, Pageable pages);
	
	//Filtrar pelo numero	
	Page<Ticket> findByNumber(Integer number, Pageable pages);
	
		
}
