package com.kanner.services;

import javax.jdo.PersistenceManager;
import com.kanner.domain.UserClass;
import com.kanner.factory.PMF;

public class UserClassSvc {
	
	private PersistenceManager pm = PMF.get().getPersistenceManager();
	
	public UserClass create(UserClass userClass) {
		
		UserClass createdUserClass = null;
		
		try {
			
			createdUserClass = pm.makePersistent(userClass);
			
		} finally {
			
			pm.close();
		}
		
		return createdUserClass;
	}
	
	public UserClass getUserClassByName(String name) {
		
		return pm.getObjectById(UserClass.class, name);
		
	}
}
