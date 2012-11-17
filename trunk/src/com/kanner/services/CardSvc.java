package com.kanner.services;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.kanner.domain.Card;
import com.kanner.factory.PMF;

public class CardSvc {
	
	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
	public Card create(Card card) {
		
		Card createdCard = null;
		
		// Save the Card
		try {
			
			createdCard = pm.makePersistent(card);
			
		} finally {
			
			pm.close();
		}
		
		return createdCard;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Card findById(@PathParam("id") String id) {
		
		pm = PMF.get().getPersistenceManager();
		
		Key k = KeyFactory.createKey(Card.class.getSimpleName(), Long.parseLong(id));
		
		return pm.getObjectById(Card.class, k);
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Card> list() {
		
		List<Card> results = null;
		Query q = pm.newQuery(Card.class);
		q.setOrdering("id desc");
		
		try {
			
			results = (List<Card>) q.execute();
			
		} finally {
			
			q.closeAll();
		}
		
		return results;
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") String id) {
		
		Card c = pm.getObjectById(Card.class, Long.parseLong(id));
		
		pm.deletePersistent(c);
	}

}
