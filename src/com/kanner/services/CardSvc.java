package com.kanner.services;

import java.text.ParseException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.kanner.domain.Card;
import com.kanner.factory.PMF;

/**
 * 
 * @author dougk_000
 *
 */
public class CardSvc {
	
	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
	/**
	 * 
	 * @param card
	 * @return
	 */
	public Card create(Card card) {
		
		Card createdCard = null;
		
		// Save the Card
		try {
			
			createdCard = pm.makePersistent(card);
			
			createdCard.setId(createdCard.getId());
			
			createdCard = pm.makePersistent(createdCard);
			
		} finally {
			
			pm.close();
		}
		
		return createdCard;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Card findById(String id) {
		
		pm = PMF.get().getPersistenceManager();
		
		return pm.getObjectById(Card.class, id);
	}
	
	/**
	 * 
	 * @param card
	 * @return
	 */
	public boolean update(Card card) {

		boolean updated = false;
		
		// Get the card already stored in the datastore
		Card currentCard = findById(card.getId());
		System.out.println("currentCard ID = " + currentCard.getId());
		
		if (currentCard.equals(card)) {
			
			System.out.println("The currentCard and the passed in card are equal");
			updated = true;
			
		} else {
			
			card.setKey(currentCard.getKey());
			
			System.out.println("I'm actually updating the card.");
			
			try {
				
				pm.makePersistent(card);
				updated = true;
				
			} finally {
				
				pm.close();
			}
		}
		
		return updated;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void delete(String id) {
		
		Card c = pm.getObjectById(Card.class, id);
		
		pm.deletePersistent(c);
	}
	
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
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
}
