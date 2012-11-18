package com.kanner.business.card;

import java.util.List;

import javax.ws.rs.WebApplicationException;

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
		
		if (createdCard == null) {
			
			throw new WebApplicationException(400);
		}
		
		// Get the available queue
		QueueSvc queueSvc = new QueueSvc();
		Queue queue = queueSvc.getQueueByOwner("Available");
		
		// If the queue doesn't exist then create it 
		if (queue == null) {
			
			queue = new Queue();
			queue.setOwner("Available");
			
		}
		
		System.out.println("Queue Owner = " + queue.getOwner());
		System.out.println("Card Key    = " + createdCard.getId());
		
		// Add the card to the list of cards in the queue
		queue.getCardList().add(createdCard);
		
		queueSvc.create(queue);
		
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
