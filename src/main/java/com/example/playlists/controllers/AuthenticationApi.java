package com.example.playlists.controllers;

import com.example.playlists.entitys.LoginRequest;
import com.example.playlists.services.TokenService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthenticationApi {

    @Inject
    TokenService tokenService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest request) {

        String username = request.getUsername();
        String password = request.getPassword();

        if (username.equals("Quadros") && password.equals("dcZ9Nd1ev+H]2SQ7Z;o*0Z,CG")) {
            String token = tokenService.generateToken(username);
            return Response.ok(token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
