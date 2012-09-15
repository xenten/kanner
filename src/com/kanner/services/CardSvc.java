package com.kanner.services;

import java.text.ParseException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.kanner.domain.Card;
import com.kanner.factory.PMF;

@Path("/card")
public class CardSvc {
	
	PersistenceManager pm = PMF.get().getPersistenceManager();

	@PUT
	@Produces(MediaType.TEXT_XML)
	@Consumes(MediaType.TEXT_XML)
	public String putCardXml(Card card) throws ParseException {
		
		pm = PMF.get().getPersistenceManager();
		
		try {
			
			pm.makePersistent(card);
			
		} finally {
			
			pm.close();
		}
		
		return card.getId().toString();
		
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String putCardJson(Card card) throws ParseException {
		
		pm = PMF.get().getPersistenceManager();
		
		try {
			
			pm.makePersistent(card);
			
		} finally {
			
			pm.close();
		}
		
		return card.getId().toString();
		
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
