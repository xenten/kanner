package com.kanner.resources;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.kanner.business.card.CardManager;
import com.kanner.business.card.CardManagerJdo;
import com.kanner.domain.Card;
import com.kanner.services.CardSvc;

@Path("/card")
public class CardResource {
		
	private static final Logger log = Logger.getLogger(CardSvc.class.getName());
	private CardManager manager = new CardManagerJdo();
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Card createNewCard(Card card) {
		
		System.out.println("Hello!!");
		
		Card createdCard = null;
		
		log.info("Entering createNewCard...");
		
		createdCard = manager.create(card);
		
		log.info("Leaving createNewCard...");
		
		if (createdCard == null) {
			
			throw new WebApplicationException(400);
		}
		
		return createdCard;
	}
	
	@GET
	@Path("/all")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Card> getAll() {
		
		List<Card> returnedCards = null;
		
		returnedCards = manager.list();
		
		if (returnedCards == null) {
			
			throw new WebApplicationException(400);
		}
		
		return returnedCards;
	}
	
	@GET
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Card get(@PathParam("id") String id) {
		
		Card returnedCard = null;
		
		returnedCard = manager.read(id);
		
		if (returnedCard == null) {
			
			throw new WebApplicationException(400);
		}
		
		return returnedCard;
	}
}
