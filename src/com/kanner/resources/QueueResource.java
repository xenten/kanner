package com.kanner.resources;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kanner.business.queue.QueueManagerJdo;
import com.kanner.domain.Queue;

@Path("/que/")
public class QueueResource {
	
	@PUT
	public Queue create(Queue queue) {
		
		return null;
	}
	
	@GET
	@Path("owner/{owner}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Queue getQueue(@PathParam("owner") String owner) {
		
		QueueManagerJdo manager = new QueueManagerJdo();
		return manager.queueByOwner(owner);
	}
	

}
