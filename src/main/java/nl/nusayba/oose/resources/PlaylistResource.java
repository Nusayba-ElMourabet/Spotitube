package nl.nusayba.oose.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.dto.PlaylistsDTO;
import nl.nusayba.oose.domain.dto.TracksDTO;
import nl.nusayba.oose.domain.services.PlaylistService;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.nusayba.oose.domain.services.TrackService;

import javax.sound.midi.Track;
import java.util.List;


@Path("/playlists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlaylistResource {

    private PlaylistService playlistService;
    private TrackService trackService;

    @Inject
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Inject
    public void setTrackService(TrackService trackService){
        this.trackService = trackService;
    }

    @GET
    public PlaylistsDTO getAllPlaylists(@QueryParam("token") String token) {
            return playlistService.getAllPlaylists(token);
    }

    // Deze moeten geen Response meer terug geven maar dit later fixen..
    @GET
    @Path("/{id}")
    public Response getPlaylistById(@PathParam("id") int id) {
        PlaylistDTO playlist = playlistService.getPlaylistById(id);
        if (playlist != null) {
            return Response.ok(playlist).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Playlist not found").build();
        }
    }

    @POST
    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO playlistDTO) {
        try {
            PlaylistsDTO playlists = playlistService.addPlaylist(token, playlistDTO);
            return Response.ok(playlists).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid token").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        try {
            PlaylistsDTO playlists = playlistService.deletePlaylist(token, id);
            return Response.ok(playlists).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid token").build();
        }
    }

    @GET
    @Path("/{id}/tracks")
    public TracksDTO getTracksInPlaylist(@PathParam("id") int id, @QueryParam("token") String token){
        return playlistService.getAllTracksInPlaylist(id, token);
    }
}

//@Path("/playlist")
//public class PlaylistResource {
//    //geen field injection gebruiken
//
//}
