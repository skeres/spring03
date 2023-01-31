package com.example.demo.controller;

import com.example.demo.entity.Etudiant;
import com.example.demo.service.IEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class EtudiantController {

        @Autowired
        IEtudiantService iEtudiantService;

        @GetMapping("etudiants")
        public ResponseEntity<List<Etudiant>> getAllEtudiants() {
                List<Etudiant> arEtudiant = new ArrayList<Etudiant>();
                arEtudiant=iEtudiantService.findAll();
                return new ResponseEntity<>(arEtudiant, HttpStatus.OK);
        }

        @GetMapping("/etudiant/{id}")
        public ResponseEntity<Etudiant> getEtudiantById(@PathVariable("id") long id) {
            Optional<Etudiant> etudiantData = iEtudiantService.findById(id);

            if (etudiantData.isPresent()) {
                return new ResponseEntity<>(etudiantData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }


}
