package com.projet.gestionJeux.repositories;

import com.projet.gestionJeux.models.EditeurJeu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EditeurJeuRepository extends JpaRepository<EditeurJeu,Integer> {
	EditeurJeu findById(int editeurId);
}
