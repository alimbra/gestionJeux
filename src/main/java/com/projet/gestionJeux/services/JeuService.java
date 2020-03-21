package com.projet.gestionJeux.services;

import com.projet.gestionJeux.models.Jeu;
import com.projet.gestionJeux.repositories.JeuRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JeuService {

  @Autowired
  JeuRepository jeuRepository;
  
  /**
   * Recupèrer les jeux correspondant aux filtres sur :
   * 	- type
   * 	- genre
   * 	- theme
   * 	- nombre de joueurs (max et min)
   * 	- age minimum conseillé
   * 	- editeur
   * 
   * Afficher ces jeux avec leurs notes triés par ordre décroissant
   * 
   */
  public List<Jeu> getJeuxByFiltre(int idType, int idGenre, int idTheme, int nbJMin, int nbJMax, int ageMin, int idEditeur) {
	  return jeuRepository.findByTypeGenreThemeNbJMinNbJMaxAgeMinEditeur(idType, idGenre, idTheme, nbJMin, nbJMax, ageMin, idEditeur);
  }
  
  
  /**
   * Supprimer les jeux associés à un éditeur
   */
  public void deleteJeuxByIdEditeur(int idEditeur) {
	  jeuRepository.deleteByIdEditeur(idEditeur);
  }


	public List<Jeu> findAll() {
		return this.jeuRepository.findAll(); 
	}
}
