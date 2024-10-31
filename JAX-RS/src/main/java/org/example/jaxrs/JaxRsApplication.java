package org.example.jaxrs;

import org.example.jaxrs.entities.Compte;
import org.example.jaxrs.entities.TypeCompte;
import org.example.jaxrs.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class JaxRsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaxRsApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository) {
        return args -> {
            compteRepository.save(new Compte(1L, 100 , new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(2L, 200 , new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(3L, 300 , new Date(), TypeCompte.COURANT));

            compteRepository.findAll().forEach(System.out::println);
        };
    }
}
