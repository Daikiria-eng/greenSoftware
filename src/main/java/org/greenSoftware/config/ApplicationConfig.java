package org.greenSoftware.config;

import org.glassfish.jersey.server.ResourceConfig;
import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/api/v1")
public class ApplicationConfig extends ResourceConfig{
    public ApplicationConfig(){
        //register(getClass());
        packages("org.greenSoftware.controller");
    }
}