package nl.nusayba.oose.resources;

import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.services.PlaylistService;
import nl.nusayba.oose.domain.services.TrackService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/playlists")
@Produces(MediaType.APPLICATION_JSON)
public class PlaylistResource {

    private PlaylistService playlistService = new PlaylistService(new TrackService());

    @GET
    public Response getPlaylists(@QueryParam("token") String token) {
        List<PlaylistDTO> playlists = playlistService.getPlaylists(token);
        if (playlists != null) {
            return Response.ok(playlists).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid token")
                    .build();
        }
    }

    @GET
    @Path("/specific")
    public Response getPlaylistById(@QueryParam("token") String token, @QueryParam("playlistId") int playlistId) {
        PlaylistDTO playlist = playlistService.getPlaylistById(token, playlistId);
        if (playlist != null) {
            return Response.ok(playlist).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid token or playlist not found")
                    .build();
        }
    }
}



//@Path("/playlist")
//public class PlaylistResource {
//    //geen field injection gebruiken
//
//}
