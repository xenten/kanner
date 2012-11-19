package com.kanner.services;

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.datanucleus.exceptions.NucleusObjectNotFoundException;

import com.kanner.domain.User;
import com.kanner.factory.PMF;

/**
 * 
 * @author dougk_000
 *
 */
public class UserSvc {
	
	/**
	 * 
	 */
	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public String create(User user) {
		
		Boolean created = false;
		User currentUser = readUserById(user.getEmail());
		
		if (currentUser == null) {
			
			pm = PMF.get().getPersistenceManager();
			
			// THere is no current user with that email so we can add
			// them.
			try {
				
				pm.makePersistent(user);
				created = true;
				
			} finally {
				
				pm.close();
			}
		}
		
		return created.toString();
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public User readUserById(String email) {
		
		User user = null;
		
		try {
			
			user = pm.getObjectById(User.class, email);
			
		} catch(JDOObjectNotFoundException ex) {
			
			// Not finding an object is not an error
			System.out.println("No Object found");
			user = null;
			
		} finally {
			
			pm.close();
		}
		
		return user;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public String update(User user) {
		
		Boolean updated = false;
		User currentUser = readUserById(user.getEmail());
		
		if (currentUser != null) {
			
			try {
				
				pm.makePersistent(user);
				updated = true;
				
			} finally {
				
				pm.close();
			}
		}
		
		return updated.toString();
	}
	
	/**
	 * 
	 * @param user
	 */
	public void delete(User user) {
		
		pm.deletePersistent(user);
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> list() {
		
		List<User> results = null;
		Query q = pm.newQuery(User.class);
		q.setOrdering("email desc");
		
		try {
			
			results = (List<User>) q.execute();
			
		} finally {
			
			q.closeAll();
		}
		
		return results;
	}
}
