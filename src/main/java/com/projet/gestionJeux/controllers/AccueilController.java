package com.projet.gestionJeux.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.projet.gestionJeux.models.Jeu;
import com.projet.gestionJeux.models.NoteJeu;
import com.projet.gestionJeux.services.JeuService;

@Controller
public class AccueilController {
	
	private final JeuService jeuService;
	
	public AccueilController(JeuService jeuService) {
		this.jeuService = jeuService; 
	}
	
	/**
	 * 
	 * @param request
	 * @return La liste des jeux
	 */
	@GetMapping("/")
	public String gestionMeteos(HttpServletRequest request, ModelMap model) {

		List<Jeu> jeux = jeuService.findAll(); 
		
		for (int i = 0; i < 20; i++) {
			Jeu jeu = new Jeu(); 
			jeu.setAge_minimum(5 + i);
			jeu.setNom_jeu("Call of "+ i);
			jeu.setNombre_joueurs_maximum(1+i);
			jeu.setNombre_joueurs_minimum(1);

			NoteJeu noteJeu = new NoteJeu(5+i, "M. X"+i); 
			jeu.setNoteJeu(noteJeu);
			jeux.add(jeu); 
		}
		
		
        model.put("jeux", jeux);
        

		return "Accueil/GestionJeux";
	}
	
}
