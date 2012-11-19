package com.kanner.resources;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.kanner.domain.Card;
import com.kanner.factory.PMF;
import com.kanner.services.CardSvc;

@Path("/card")
public class CardResource {
		
	private static final Logger log = Logger.getLogger(CardSvc.class.getName());
	private CardSvc cardSvc = new CardSvc();
	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String create(Card card) {
		
		Card createdCard = null;
		
		log.info("Entering createNewCard...");
		
		createdCard = cardSvc.create(card, pm);
		
		System.out.println("createdCard = " + createdCard.getId());
		
		if (createdCard.getKey() == null) {
			
			throw new WebApplicationException(400);
		}
		
		log.info("Leaving createNewCard...");
		
		pm.close();
		
		return createdCard.getId();
	}
	
	@PUT
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String update(Card card) {
		
		Boolean cardUpdated = false;
		
		System.out.println("Entering update...");
		
		if (card.getId() == null) {
			
			throw new WebApplicationException(400);
		}
		
		cardUpdated = cardSvc.update(card, pm);
		
		System.out.println("cardUpdated = " + cardUpdated);
		System.out.println("Exiting update...");
		
		pm.close();
		
		return cardUpdated.toString();
	}
	
	@GET
	@Produces({	MediaType.APPLICATION_JSON, 
				MediaType.APPLICATION_XML, 
				MediaType.APPLICATION_XHTML_XML})
	public List<Card> list() {
		
		List<Card> returnedCards = null;
		
		returnedCards = cardSvc.list(pm);
		
		if (returnedCards == null) {
			
			throw new WebApplicationException(400);
		}
		
		pm.close();
		
		return returnedCards;
	}
	
	@GET
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Card get(@PathParam("id") String id) {
		
		Card returnedCard = null;
		
		returnedCard = cardSvc.findById(id, pm);
		
		pm.close();
		
		return returnedCard;
	}
}
