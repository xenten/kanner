package com.kanner.business.card;

import javax.jdo.PersistenceManager;

import com.kanner.domain.Card;
import com.kanner.domain.Queue;
import com.kanner.factory.PMF;
import com.kanner.services.CardSvc;
import com.kanner.services.EmailSvc;
import com.kanner.services.QueueSvc;


public class CardManagerJdo implements CardManager {
	
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	@Override
	public Card create(Card card) {
		
		// Create a new card
		CardSvc cardSvc = new CardSvc();
		Card createdCard = cardSvc.create(card);
		
		// Get the available queue
		QueueSvc queueSvc = new QueueSvc();
		Queue availableQueue = queueSvc.getQueueByOwner("Available");
		
		// If the queue doesn't exist then create it 
		if (availableQueue == null) {
			
			Queue queue = new Queue();
			queue.setOwner("Available");
			
			availableQueue = queueSvc.create(queue);
			
		}
		
		// Add the card to the list of cards in the queue
		availableQueue.getCardList().add(createdCard);
		
		// Email Confirmation to Owner
		EmailSvc emailSvc = new EmailSvc();
		emailSvc.sendEmail();
		
		return createdCard;
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
