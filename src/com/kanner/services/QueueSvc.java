package com.kanner.services;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.kanner.domain.Queue;
import com.kanner.factory.PMF;

public class QueueSvc {

	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
	public Queue create(Queue queue) {
		
		Queue createdQueue = null;
		
		try {
			
			createdQueue = pm.makePersistent(queue);
			
		} finally {
			
			pm.close();
		}
		
		return createdQueue;
	}
	
	public Queue getQueueByOwner(String owner) {
		
		Queue queue = null;
		Query query = pm.newQuery(Queue.class);
		query.setFilter("owner == ownerParam");
		query.setRange(0, 0);
		query.declareParameters("String ownerParam");
		
		try {
			
			queue = (Queue) query.execute(owner);
			
		} finally {
			
			query.closeAll();
		}
		
		return queue;
	}
}
