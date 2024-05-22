package nl.nusayba.oose.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import  nl.nusayba.oose.domain.dto.TrackDTO;
import  nl.nusayba.oose.domain.services.TrackService;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/tracks")
public class TrackResource {

    @Inject
    private TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TrackDTO> getAllTracks() {
        return trackService.getAllTracks();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TrackDTO getTrackById(@PathParam("id") int id) {
        return trackService.getTrackById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrack(TrackDTO track) {
        trackService.addTrack(track);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTrack(TrackDTO track) {
        trackService.updateTrack(track);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTrack(@PathParam("id") int id) {
        trackService.deleteTrack(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}