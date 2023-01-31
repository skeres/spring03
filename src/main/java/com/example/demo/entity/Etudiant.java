package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity //signifie que la classe doit être persistée. Créé une entité JPA
@Data // annotation Lombock qui génère les getters et les setters dans le byte code, mais pas dans le source. Pour plus de "visibilité".
@AllArgsConstructor   // annotation Lombock : créé un contructeur avec tous les paramètres
@NoArgsConstructor  // annotation Lombock : créé un contructeur SANS paramètres
public class Etudiant {
    @Id // spécifie que l'attribut ci dessous est la primaryKey de l'entité
    @GeneratedValue(strategy = GenerationType.IDENTITY) // type auto incrément
    private Long id;
    private String nom;
    private String prenom;
    private Date dateNaissance;

}
