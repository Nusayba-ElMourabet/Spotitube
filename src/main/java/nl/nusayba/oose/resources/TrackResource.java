package nl.nusayba.oose.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import nl.nusayba.oose.domain.dto.TracksDTO;
import nl.nusayba.oose.domain.services.PlaylistService;
import jakarta.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/tracks")
public class TrackResource {

    private PlaylistService playlistService;

    @Inject
    public void setPlaylistService(PlaylistService playlistService){this.playlistService = playlistService;}


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public TracksDTO getTracksNotInPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistId) {
        return playlistService.getTracksNotInPlaylist(playlistId, token);
    }
}
