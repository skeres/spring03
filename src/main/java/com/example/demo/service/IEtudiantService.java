package com.example.demo.service;

import com.example.demo.entity.Etudiant;
import java.util.List;
import java.util.Optional;

public interface IEtudiantService {

    List<Etudiant> findAll();
    Optional<Etudiant> findById(Long id);
    List<Etudiant> findByNom(String nom);
    List<Etudiant> findByPrenom(String nom);
}
