package com.kanner.business.card;

import javax.jdo.PersistenceManager;

import com.kanner.domain.Card;
import com.kanner.factory.PMF;


public class CardManagerJdo implements CardManager {
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	@Override
	public Card create(Card card) {

		try {
			
			pm.makePersistent(card);
			
		} finally {
			
			pm.close();
		}
		
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
