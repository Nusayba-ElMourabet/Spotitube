package nl.nusayba.oose.resources;

import jakarta.inject.Inject;
import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.services.PlaylistService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/playlists")
public class PlaylistResource {

    @Inject
    private PlaylistService playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        List<PlaylistDTO> playlists = playlistService.getPlaylists(token);
        if (playlists == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(playlists).build();
    }

    @GET
    @Path("/specific")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylistById(@QueryParam("token") String token, @QueryParam("playlistId") int playlistId) {
        PlaylistDTO playlist = playlistService.getPlaylistById(token, playlistId);
        if (playlist == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(playlist).build();
    }
}

//@Path("/playlist")
//public class PlaylistResource {
//    //geen field injection gebruiken
//
//}
