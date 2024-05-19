package nl.nusayba.oose.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/")
public class HelloWorldResource {

    @GET
    public String hello() { return "Hello World!"; }
}
