package com.projet.gestionJeux.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.projet.gestionJeux.models.Jeu;
import com.projet.gestionJeux.models.TypeJeu;
import com.projet.gestionJeux.services.TypeJeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class TypeJeuController {

	//@Autowired
 	private final TypeJeuService typeJeuService;
	
	public TypeJeuController(TypeJeuService typeJeuService) {
		this.typeJeuService = typeJeuService;
	}
	

	/**
	 * La liste des types
	 * @return la liste des types de jeu
	 */
	@GetMapping("/types")
	public String getTypeJeux(HttpServletRequest request, ModelMap model) {
		List<TypeJeu> typeJeux = typeJeuService.getTypeJeux();
		
		for (int i = 0; i < 20; i++) {
			TypeJeu typeJeu = new TypeJeu();
			typeJeu.setNom_type("Type " + i);
			typeJeux.add(typeJeu);
		}
		
		model.put("typeJeux", typeJeux);
		
		return "TypesJeux";
	}
	
	/**
	 * Créer un type
	 */
	@PostMapping("types")
	public void creerOuModifierType(TypeJeu typeJeu) {
		this.typeJeuService.saveOrUpdate(typeJeu);
	}
	
	/**
	 * Supprimer un type
	 */
	@DeleteMapping("/types/{typeId}")
	public void supprimerType(int id) {
		this.typeJeuService.deleteTypeJeu(id);
	}
}
