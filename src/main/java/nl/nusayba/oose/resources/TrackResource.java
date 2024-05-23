package nl.nusayba.oose.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import nl.nusayba.oose.datasource.TrackDAO;
import nl.nusayba.oose.domain.dto.TrackDTO;
import nl.nusayba.oose.domain.dto.TracksDTO;
import nl.nusayba.oose.domain.services.TrackService;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/tracks")
public class TrackResource {

    private TrackService trackService;

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrackById(@PathParam("id") int id) {
        TrackDTO track = trackService.getTrackById(id);
        if (track != null) {
            return Response.ok(track).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Track not found").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrack(TrackDTO trackDTO) {
        try {
            TracksDTO tracks = trackService.addTrack(trackDTO);
            return Response.ok(tracks).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error adding track").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrack(@PathParam("id") int id) {
        try {
            TracksDTO tracks = trackService.deleteTrack(id);
            return Response.ok(tracks).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting track").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTrack(@PathParam("id") int id, TrackDTO trackDTO) {
        try {
            TracksDTO tracks = trackService.updateTrack(id, trackDTO);
            return Response.ok(tracks).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating track").build();
        }
    }
}
