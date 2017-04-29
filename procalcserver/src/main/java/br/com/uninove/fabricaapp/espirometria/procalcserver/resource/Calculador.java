package br.com.uninove.fabricaapp.espirometria.procalcserver.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/calc")
public class Calculador {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response teste(){
		return Response.ok().build();
	}
}
