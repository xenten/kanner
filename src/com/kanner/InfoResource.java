package com.kanner;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/test/")
public class InfoResource {

	@GET
	@Produces("text/plain")
	@Path("/info")
	public String info(@DefaultValue("John Doe") @QueryParam("name") String name) {
		
		return "Hello " + name + " this is INFO from GET";
	}
}
