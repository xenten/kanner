package com.kanner.resources;

import java.text.ParseException;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	public Card createNewCard(Card card) throws ParseException {
		
		Card createdCard = null;
		
		log.info("Entering createNewCard...");
		
		createdCard = manager.create(card);
		
		log.info("Leaving createNewCard...");
		
		return createdCard;
	}
}
