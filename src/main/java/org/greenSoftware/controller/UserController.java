package org.greenSoftware.controller;

import org.greenSoftware.dto.UserDTO;
import org.greenSoftware.manager.Manager;
import org.greenSoftware.manager.impl.ManagerImpl;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class UserController {
    Manager manager=new ManagerImpl();

    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUser(UserDTO user){
        return Response.ok(manager.insertUser(user)).build();
    }
    
    @POST
    @Path("/validate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateUser(UserDTO user){
        return Response.ok(manager.validateUser(user)).build();
    }
    
    @POST
    @Path("/verify-user")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response verifyUser(@Context HttpHeaders headers,UserDTO user){
        return Response.ok(manager.verifyUser(user,headers.getHeaderString("Authentication"))).build();
    }
}