package com.kanner.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.kanner.domain.User;
import com.kanner.services.UserSvc;

@Path("/user")
public class UserResource {
	
	UserSvc userSvc = new UserSvc();
	
	// Create
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String create(User user) {
		
		return userSvc.create(user);
	}
	
	// Read
	// Update
	// Delete
	
	// List
	@GET
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<User> list() {
		
		return userSvc.list();
	}
}
