package nl.nusayba.oose.resources;

import  nl.nusayba.oose.domain.dto.TrackDTO;
import  nl.nusayba.oose.domain.services.TrackService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/tracks")
@Produces(MediaType.APPLICATION_JSON)
public class TrackResource {

    private TrackService trackService = new TrackService();

    @GET
    public Response getTracks(@QueryParam("token") String token) {
        List<TrackDTO> tracks = trackService.getTracks(token);
        if (tracks != null) {
            return Response.ok(tracks).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid token")
                    .build();
        }
    }
}
