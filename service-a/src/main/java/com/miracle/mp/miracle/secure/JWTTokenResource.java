package com.miracle.mp.miracle.secure;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;

@RequestScoped
@Path("jwt")
public class JWTTokenResource {
		
	@Inject
	Principal principal;
	
	@Inject
	JsonWebToken token;
	
	@GET
	@RolesAllowed("admin")
	@Path("user")
	public Response getJWTUserName() {
		return Response.ok(token.getName()).build();
	}
	
	@GET
	@RolesAllowed("admin")
	@Path("group")
	public Response getJWTGroups() {
		return Response.ok(token.getGroups().toString()).build();
	}
	
	@GET
	public Response getExampleForJWT() {
		JsonObject miracle = Json.createObjectBuilder()
							.add("team", "miracle")
							.add("members", "7").build();
		return Response.ok(miracle).build();
	}
}
