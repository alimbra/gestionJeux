package com.projet.gestionJeux.controllers;

import java.util.List;

import com.projet.gestionJeux.models.Jeu;
import com.projet.gestionJeux.services.JeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("jeux")
public class JeuController {

	@Autowired
	private JeuService jeuService;

	/**
	 * La liste des jeux 
	 * @return List<Jeu>
	 */
	@PostMapping("/jeux")
	public List<Jeu> getGenreJeux(int idType, int idGenre, int idTheme, int nbJMin, int nbJMax, int ageMin, int idEditeur) {
		return jeuService.getJeuxByFiltre(idType, idGenre, idTheme, nbJMin, nbJMax, ageMin, idEditeur);
	}
	
	/**
	 * Supprimer jeux associé à un éditeur
	 */
	@DeleteMapping("/genres")
	public void supprimerGenre(int idEditeur) {
		this.jeuService.deleteJeuxByIdEditeur(idEditeur);
	}
}
