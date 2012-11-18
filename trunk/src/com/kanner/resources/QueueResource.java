package com.kanner.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kanner.business.queue.QueueManagerJdo;
import com.kanner.domain.Queue;

@Path("/queue")
public class QueueResource {
	
	@PUT
	public Queue create(Queue queue) {
		
		return null;
	}
	
	@GET
	@Path("/all")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Queue> listAll() {
		
		QueueManagerJdo manager = new QueueManagerJdo();
		
		return manager.list();
	}
	
	@GET
	@Path("/owner/{owner}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Queue getQueue(@PathParam("owner") String owner) {
		
		QueueManagerJdo manager = new QueueManagerJdo();
		return manager.queueByOwner(owner);
	}
	

}
