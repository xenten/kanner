package com.kanner.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kanner.domain.Queue;
import com.kanner.services.QueueSvc;

@Path("/queue")
public class QueueResource {
	
	private QueueSvc queueSvc = new QueueSvc();
	
	@POST
	public Long create(Queue queue) {
		
		return queueSvc.create(queue);
	}
	
	@GET
	@Path("/owner/{owner}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Queue readByOwner(@PathParam("owner") String owner) {
		
		return queueSvc.getQueueByOwner(owner);
	}
	
	@PUT
	public String update(Queue queue) {
		
		return queueSvc.update(queue);
	}
	
	@GET
	@Path("/all")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Queue> list() {
		
		return queueSvc.list();
	}
}
