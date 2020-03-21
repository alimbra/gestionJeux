package com.projet.gestionJeux.controllers;

import com.projet.gestionJeux.models.EditeurJeu;
import com.projet.gestionJeux.services.EditeurJeuService;
import com.projet.gestionJeux.services.JeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("editeurs")
public class EditeurJeuController {

	@Autowired
	private EditeurJeuService editeurJeuService;
	@Autowired
	private JeuService jeuService;



	
	@GetMapping("/editeurs")
	public List<EditeurJeu> getEditeurJeux() {
		return editeurJeuService.getEditeurJeux();
	}
	
	/**
	 * Créer un éditeur
	 */
	@PostMapping("editeurs")
	public void creerOuModifierEditeur(EditeurJeu editeurJeu) {
		this.editeurJeuService.saveOrUpdate(editeurJeu);
	}
	
	/**
	 * Supprimer un éditeur
	 * Un éditeur supprimé, supprimera tous les jeux associés à ce éditeur
	 */
	@DeleteMapping("/editeurs/{editeurId}")
	public void supprimerEditeur(int id) {
		//Gérer d'abord la suppression des jeux associés
		this.jeuService.deleteJeuxByIdEditeur(id);
		
		//Supprimer l'éditeur
		this.editeurJeuService.deleteEditeurJeu(id);
	}
	
}
