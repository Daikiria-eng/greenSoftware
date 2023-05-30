package org.greenSoftware.controller;

import org.greenSoftware.manager.Manager;
import org.greenSoftware.manager.impl.ManagerImpl;
import org.greenSoftware.dto.ModuleDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/modules")
public class ModuleController {
    Manager manager=new ManagerImpl();

    @GET
    @Path("/get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllModules(@Context HttpHeaders headers){
        String pass=headers.getHeaderString("pass-code");
        //return manager.getAllModules();
        return (pass.equals("admin"))?
            Response.ok(manager.getAllModules()).build():
            Response.ok("{\"Response\":\"You Haver No Permission\"}").build();
    }
    
    @POST
    @Path("/get-by-name")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getModule(@Context HttpHeaders headers,ModuleDTO module){
        return Response.ok(manager.getModule(module)).build();
    }
    
    @POST
    @Path("/get-questions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestions(ModuleDTO module){
        return Response.ok(manager.getQuestions(module)).build();
    }

    @POST
    @Path("/get-content")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContent(ModuleDTO module){
        return Response.ok(manager.getContent(module)).build();
    }
}