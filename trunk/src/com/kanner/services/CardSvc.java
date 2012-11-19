package com.kanner.services;

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
	
	/**
	 * 
	 * @param card
	 * @return
	 */
	public Card create(Card card, PersistenceManager pm) {
		
		Card createdCard = null;
			
		createdCard = pm.makePersistent(card);
		
		// This is an odd thing to do...
		// Necessary thought, as far as I can tell
		createdCard.setId(createdCard.getId());
		
		createdCard = pm.makePersistent(createdCard);
		
		return createdCard;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Card findById(String id, PersistenceManager pm) {
		
		pm = PMF.get().getPersistenceManager();
		
		return pm.getObjectById(Card.class, id);
	}
	
	/**
	 * 
	 * @param card
	 * @return
	 */
	public boolean update(Card card, PersistenceManager pm) {

		boolean updated = false;
		
		// Get the card already stored in the datastore
		Card currentCard = findById(card.getId(), pm);
		System.out.println("currentCard ID = " + currentCard.getId());
		
		if (currentCard.equals(card)) {
			
			System.out.println("The currentCard and the passed in card are equal");
			updated = true;
			
		} else {
			
			card.setKey(currentCard.getKey());
			
			System.out.println("I'm actually updating the card.");
			
			pm.makePersistent(card);
			updated = true;
		}
		
		return updated;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void delete(String id, PersistenceManager pm) {
		
		Card c = pm.getObjectById(Card.class, id);
		
		pm.deletePersistent(c);
	}
	
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Card> list(PersistenceManager pm) {
		
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
