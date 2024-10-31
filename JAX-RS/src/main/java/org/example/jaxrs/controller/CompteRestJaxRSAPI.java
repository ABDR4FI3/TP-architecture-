package org.example.jaxrs.controller;

import jakarta.ws.rs.*;
import org.example.jaxrs.entities.Compte;
import org.example.jaxrs.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
@Path("/banque")
public class CompteRestJaxRSAPI {
    @Autowired
    private CompteRepository compteRepository;

    @Path("/comptes")
    @GET
    @Produces({jakarta.ws.rs.core.MediaType.APPLICATION_XML , jakarta.ws.rs.core.MediaType.APPLICATION_JSON})
    public List<Compte> getComptes() {
        return compteRepository.findAll();
    }
    // READ: Recuperer un compte par son identifiant
    @Path("/comptes/{id}")
    @GET
    @Produces({"application/xml", "application/json"})
    public Compte getCompte(@PathParam("id") Long id) {
        return compteRepository.findById(id).orElse(null);
    }

    // CREATE: Ajouter un nouveau compte
    @Path("/comptes")
    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Compte addCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    // UPDATE: Mettre ajour un compte existant
    @Path("/comptes/{id}")
    @PUT
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Compte updateCompte(@PathParam("id") Long id, Compte compte) {
        Compte existingCompte = compteRepository.findById(id).orElse(null);
        if (existingCompte != null) {
            existingCompte.setSolde(compte.getSolde());
            existingCompte.setDateCreation(compte.getDateCreation());
            existingCompte.setType(compte.getType());
            return compteRepository.save(existingCompte);
        }
        return null;
    }

// DELETE: Supprimer un compte
    @Path("/comptes/{id}")
    @DELETE
    @Produces({"application/json"})
    public void deleteCompte(@PathParam("id") Long id) {
        compteRepository.deleteById(id);
    }
}
