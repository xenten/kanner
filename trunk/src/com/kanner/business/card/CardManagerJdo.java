package com.kanner.business.card;

import javax.jdo.PersistenceManager;

import com.kanner.domain.Card;
import com.kanner.factory.PMF;


public class CardManagerJdo implements CardManager {
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	@Override
	public Card create(Card card) {
		
		// Save the Card
		try {
			
			pm.makePersistent(card);
			
		} finally {
			
			pm.close();
		}
		
		// Send email confirmation
		Email email = new Email();
		
		
		return card;
	}

	@Override
	public Card read(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card update(Card card) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card delete(Card card) {
		// TODO Auto-generated method stub
		return null;
	}

}
