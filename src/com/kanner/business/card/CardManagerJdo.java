package com.kanner.business.card;

import java.util.Date;

import javax.jdo.PersistenceManager;

import com.kanner.domain.Card;
import com.kanner.domain.Transaction;
import com.kanner.factory.PMF;


public class CardManagerJdo implements CardManager {
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	@Override
	public Card create(Card card) {
		
		// Create a transaction
		Transaction cardTxn = new Transaction();
		cardTxn.setType(card.getClass().getSimpleName());
		cardTxn.setUser(card.getOwner());
		cardTxn.setCompleted(false);
		cardTxn.setDate(new Date());
		
		// Save the Card
		try {
			
			pm.makePersistent(card);
			cardT
			+.+++++++++xn.setCompleted(true);
			
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
