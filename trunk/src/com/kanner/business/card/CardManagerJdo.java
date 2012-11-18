package com.kanner.business.card;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.kanner.domain.Card;
import com.kanner.domain.Queue;
import com.kanner.services.CardSvc;
import com.kanner.services.EmailSvc;
import com.kanner.services.QueueSvc;


public class CardManagerJdo implements CardManager {
	
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
	
	public Card read(String id) {
		
		CardSvc cardSvc = new CardSvc();
		return cardSvc.findById(id);
	}
	
	public List<Card> list() {
		
		CardSvc cardSvc = new CardSvc();
		return cardSvc.list();
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
