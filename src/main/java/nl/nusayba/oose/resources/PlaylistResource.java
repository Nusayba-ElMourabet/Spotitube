package nl.nusayba.oose.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.services.PlaylistService;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/playlists")
public class PlaylistResource {

    @Inject
    private PlaylistService playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlaylistDTO> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PlaylistDTO getPlaylistById(@PathParam("id") int id) {
        return playlistService.getPlaylistById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlist) {
        playlistService.addPlaylist(playlist);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlaylist(PlaylistDTO playlist) {
        playlistService.updatePlaylist(playlist);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePlaylist(@PathParam("id") int id) {
        playlistService.deletePlaylist(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

//@Path("/playlist")
//public class PlaylistResource {
//    //geen field injection gebruiken
//
//}
