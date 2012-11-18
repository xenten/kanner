package com.kanner.business.queue;

import java.util.List;

import com.kanner.domain.Card;
import com.kanner.domain.Queue;
import com.kanner.services.CardSvc;
import com.kanner.services.QueueSvc;

public class QueueManagerJdo {
	
	public List<Queue> list() {
		
		QueueSvc queueSvc = new QueueSvc();
		return queueSvc.list();
	}
	
	public Queue queueByOwner(String owner) {
		
		QueueSvc queueSvc = new QueueSvc();
		
		return queueSvc.getQueueByOwner(owner);
	}
	
	public Queue move(String currentOwner, Card card, String newOwner) {
		
		QueueSvc queueSvc = new QueueSvc();
		Queue currentQueue = queueSvc.getQueueByOwner(currentOwner);
		Queue newQueue = queueSvc.getQueueByOwner(newOwner);
		
		currentQueue.getCardList().remove(card);
		newQueue.getCardList().add(card);
		
		queueSvc.update(currentQueue);
		
		return queueSvc.update(newQueue);
	}
}
