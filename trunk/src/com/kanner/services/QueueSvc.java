package com.kanner.services;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.kanner.domain.Queue;
import com.kanner.factory.PMF;

public class QueueSvc {

	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
	/**
	 * 
	 * @param queue
	 * @return
	 */
	public Long create(Queue queue) {
		
		Queue createdQueue = null;
		
		try {
			
			createdQueue = pm.makePersistent(queue);
			
		} finally {
			
			pm.close();
		}
		
		return createdQueue.getId();
	}
	
	/**
	 * 
	 * @param queue
	 * @return
	 */
	public String update(Queue queue) {
		
		Boolean updated = false;
		Queue currentQueue = getQueueById(queue.getId());
		
		if (queue.equals(currentQueue)) {
			
			updated = true;
			
		} else {
			
			try {
			
				pm.makePersistent(queue);
				updated = true;
				
			} finally {
				
				pm.close();
			}
		}
		
		return updated.toString(); 
	}
	
	
	
	public Queue getQueueById(Long id) {
		
		return pm.getObjectById(Queue.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public Queue getQueueByOwner(String owner) {
		
		List<Queue> queueList = null;
		Queue queue = null;
		Query query = pm.newQuery(Queue.class);
		query.setFilter("owner == ownerParam");
		query.setRange(0, 1);
		query.declareParameters("String ownerParam");
		
		try {
			
			queueList =  (List<Queue>) query.execute(owner);
			
			if (!queueList.isEmpty()) {
				
				System.out.println("Queue List is not empty");
				
				queue = queueList.get(0);
			}
			
		} finally {
			
			query.closeAll();
		}
		
		return queue;
	}
	
	@SuppressWarnings("unchecked")
	public List<Queue> list() {
	
		List<Queue> results = null;
		Query q = pm.newQuery(Queue.class);
		q.setOrdering("id desc");
		
		try {
			
			results = (List<Queue>) q.execute();
			
		} finally {
			
			q.closeAll();
		}
		
		return results;
	}
}
