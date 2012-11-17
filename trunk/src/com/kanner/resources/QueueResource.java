package com.kanner.resources;

import javax.ws.rs.Path;

import com.kanner.domain.Queue;

@Path("/queue")
public class QueueResource {
	
	@Path("/move/{currentOwner}/{card}/{newOwner}")
	public Queue moveCardToQueue() {
		
		return null;
	}

}
