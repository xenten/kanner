package com.kanner.resources;

import java.util.List;

import javax.ws.rs.Consumes;
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
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String create(Queue queue) {
		
		System.out.println("queue.name = " + queue.getName());
		return queueSvc.create(queue);
	}
	
	@PUT
	public String update(Queue queue) {
		
		return queueSvc.update(queue);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Queue> list() {
		//
		return queueSvc.list();

	}
	
	@GET
	@Path("/owner/{owner}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Queue readByOwner(@PathParam("owner") String owner) {
		
		return queueSvc.getQueueByOwner(owner.toLowerCase());
	}
}
