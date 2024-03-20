package com.example.demo;

import com.example.demo.entity.Etudiant;
import com.example.demo.repository.IEtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example.demo")
public class DemoApplication implements CommandLineRunner {

	@Autowired  //permet d'injecter au runtime une implémentation de cette interface
	private IEtudiantRepository iEtudiantRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//implements CommandLineRunner permet de lancer des commandes spécifiques au demarrage de l'application.
	//exemple : insérer des données dans une table
	@Override
	//methode overide de CommandLineRunner
	public void run(String... args) throws Exception {

		if (iEtudiantRepository.findAll().isEmpty()) {
			iEtudiantRepository.save(new Etudiant(null, "k", "steph", new Date()));
			iEtudiantRepository.save(new Etudiant(null, "m", "maria", new Date()));
			iEtudiantRepository.save(new Etudiant(null, "p", "phil", new Date()));
			iEtudiantRepository.save(new Etudiant(null, "j", "jessica", new Date()));
			iEtudiantRepository.save(new Etudiant(null, "z", "paul", new Date()));
		}
	}
}
