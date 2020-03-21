package com.projet.gestionJeux.controllers;

import java.util.List;

import com.projet.gestionJeux.models.TypeJeu;
import com.projet.gestionJeux.services.TypeJeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("types")
public class TypeJeuController {

	@Autowired
 	private TypeJeuService typeJeuService;
	

	/**
	 * La liste des types
	 * @return List<TypeJeu>
	 */
	@GetMapping("/types")
	public List<TypeJeu> getTypeJeux() {
		return typeJeuService.getTypeJeux();
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
