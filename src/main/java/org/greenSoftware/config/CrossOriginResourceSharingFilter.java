package org.greenSoftware.config;

import java.io.IOException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;

public class CrossOriginResourceSharingFilter implements ContainerResponseFilter{

    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
        throws IOException {
        try{
            responseContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
            responseContext.getHeaders().putSingle("access-control-allow-credentials","true");
            responseContext.getHeaders().putSingle("Access-Control-Allow-Methods","GET,POST,PUT,DELETE");
            responseContext.getHeaders().putSingle("Access-Control-Allow-Headers","Content-Type, Accept");
        }catch(UnsupportedOperationException uo){
            throw new UnsupportedOperationException("Unimplemented method 'filter'");
        }
    }
}