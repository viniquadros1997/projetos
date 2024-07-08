package com.example.playlists.controllers;

import com.example.playlists.entitys.Playlist;
import com.example.playlists.services.PlaylistService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/lists")
public class PlaylistsApi {

    @Inject
    PlaylistService playlistService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPlaylist(Playlist playlist) {

        if (playlist.nome == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Playlist savedPlaylist = playlistService.savePlaylist(playlist);

        URI location = URI.create("/lists/" + savedPlaylist.nome);
        return Response.created(location).entity(savedPlaylist).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists() {

        List<Playlist> playlist = playlistService.findAllPlaylists();

        return Response.ok(playlist).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{listName}")
    public Response getPlaylistByNome(@PathParam("listName") String listName) {

        Playlist playlist = playlistService.findPlaylistByNome(listName);

        if (playlist != null) {

            return Response.ok(playlist).build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();

        }

    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{listName}")
    public Response deletePlaylistByNome(@PathParam("listName") String listName) {

        Playlist playlist = playlistService.findPlaylistByNome(listName);

        if (playlist != null) {

            playlistService.deletePlaylistByNome(listName);
            return Response.noContent().build();

        } else {

            return Response.status(Response.Status.NOT_FOUND).build();

        }

    }

}
