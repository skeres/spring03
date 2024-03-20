package com.example.demo.service;

import com.example.demo.entity.Etudiant;
import com.example.demo.repository.IEtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements IEtudiantService {

    @Autowired
    IEtudiantRepository etudiantRepository;

    @Override
    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    @Override
    public Optional<Etudiant> findById(Long id) {
        return etudiantRepository.findById(id);
    }

    @Override
    public List<Etudiant> findByNom(String nom) {
        return etudiantRepository.findByNom(nom);
    }

    @Override
    public List<Etudiant> findByPrenom(String prenom) {
        return etudiantRepository.findByPrenom(prenom);
    }
}
