package nl.nusayba.oose.resources;

import jakarta.ws.rs.Path;


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
@Produces(MediaType.APPLICATION_JSON)
public class PlaylistResource {

    private PlaylistService playlistService = new PlaylistService();

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
}


//@Path("/playlist")
//public class PlaylistResource {
//    //geen field injection gebruiken
//
//}
