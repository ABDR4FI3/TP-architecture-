package org.example.jaxrs.Config;

import jakarta.ws.rs.*;
import org.example.jaxrs.controller.CompteRestJaxRSAPI;
import org.example.jaxrs.entities.Compte;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@Configuration
public class MyConfig {
    @Bean
    public ResourceConfig resourceConfig() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(CompteRestJaxRSAPI.class);
        return resourceConfig;
    }


}
