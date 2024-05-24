package nl.nusayba.oose.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import nl.nusayba.oose.domain.dto.PlaylistDTO;
import nl.nusayba.oose.domain.dto.PlaylistsDTO;
import nl.nusayba.oose.domain.dto.TrackDTO;
import nl.nusayba.oose.domain.dto.TracksDTO;
import nl.nusayba.oose.domain.services.PlaylistService;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/playlists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlaylistResource {

    private PlaylistService playlistService;

    @Inject
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }



    @GET
    public PlaylistsDTO getAllPlaylists(@QueryParam("token") String token) {
            return playlistService.getAllPlaylists(token);
    }

    @POST
    public PlaylistsDTO addPlaylist(@QueryParam("token") String token, PlaylistDTO playlistDTO){
        return playlistService.addPlaylist(token, playlistDTO);
    }
    @DELETE
    @Path("/{id}")
    public PlaylistsDTO deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
            return playlistService.deletePlaylist(token, id);
    }

    @PUT
    @Path("/{id}")
    public PlaylistsDTO updatePlaylist(@QueryParam("token") String token, @PathParam("id") int id, PlaylistDTO playlistDTO){
        return playlistService.updatePlaylist(token, id, playlistDTO);
    }

    @GET
    @Path("/{id}/tracks")
    public TracksDTO getTracksInPlaylist(@PathParam("id") int id, @QueryParam("token") String token){
        return playlistService.getAllTracksInPlaylist(id, token);
    }

    @POST
    @Path("/{id}/tracks")
    public TracksDTO addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int id, TrackDTO trackDTO){
        return playlistService.addTrackToPlaylist(token, id, trackDTO);
    }

    @DELETE
    @Path("/{id}/tracks/{trackId}")
    public TracksDTO deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("id") int id, @PathParam("trackId") int trackId){
        return playlistService.deleteTrackFromPlaylist(token, id, trackId);
    }
}
