package com.projet.gestionJeux.controllers;

import com.projet.gestionJeux.models.EditeurJeu;
import com.projet.gestionJeux.services.EditeurJeuService;
import com.projet.gestionJeux.services.JeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EditeurJeuController {

	//@Autowired
	private final EditeurJeuService editeurJeuService;
	@Autowired
	private JeuService jeuService;

	public EditeurJeuController(EditeurJeuService editeurJeuService) {
		this.editeurJeuService = editeurJeuService;
	}

	/**
	 * La liste des types
	 * @return La vue des éditeurs
	 */
	@GetMapping("/gestion-jeux/editeurs")
	public String getEditeurJeux(HttpServletRequest request, ModelMap model) {
		List<EditeurJeu> editeurJeux = editeurJeuService.getEditeurJeux();
		model.put("editeurs", editeurJeux);
		return "Editeur/GestionEditeur";
	}
	
	/**
	 * Créer un éditeur
	 */
	@PostMapping("/gestion-jeux/ajout-editeur")
	public String creerOuModifierEditeur(HttpServletRequest request, ModelMap model) {
		String editeurNom = (String) request.getParameter("nom");
		System.out.println(editeurNom);
		EditeurJeu editeur = new EditeurJeu();
		editeur.setNom_editeur(editeurNom);
		EditeurJeu editeurJeu = this.editeurJeuService.saveOrUpdate(editeur);
		
		if (editeurJeuService.existeEditeurJeu(editeurJeu.getId())){
			model.put("addSucess",true);
		}
		else{
			model.put("addSucess",false);
		}
		
		List<EditeurJeu> editeurs = editeurJeuService.getEditeurJeux();
		model.put("editeurs", editeurs);
		return "Editeur/GestionEditeur";
		 
	}
	
	@PostMapping("/gestion-jeux/edit-editeur")
	public String editEditeur(HttpServletRequest request, ModelMap model) {
		String editeur_id = request.getParameter("editKind_id");
		String editeurNom = (String) request.getParameter("nom");
		System.out.println(editeurNom);
		int editeurId = Integer.parseInt(editeur_id);
		EditeurJeu editeur = this.editeurJeuService.findById(editeurId);
		editeur.setNom_editeur(editeurNom); 
		EditeurJeu editeurJeu = this.editeurJeuService.saveOrUpdate(editeur);
		
		if (editeurJeuService.existeEditeurJeu(editeurJeu.getId())){
			model.put("updateSucess",true);
		}
		else{
			model.put("updateSucess",false);
		}
		
		List<EditeurJeu> editeurs = editeurJeuService.getEditeurJeux();
		model.put("editeurs", editeurs);
		return "Editeur/GestionEditeur";
		 
	}
	
	/**
	 * Supprimer un éditeur
	 * Un éditeur supprimé, supprimera tous les jeux associés à ce éditeur
	 */
	@GetMapping("/gestion-jeux/deleteEditeur/{id}")
	public String supprimerEditeur(@PathVariable("id") int id, ModelMap model)
	{
	  if (editeurJeuService.existeEditeurJeu(id)) {
		  //Gérer d'abord la suppression des jeux associés
		  //this.jeuService.deleteJeuxByIdEditeur(id);
	      this.editeurJeuService.deleteEditeurJeu(id);
	      model.put("deleteSucess",true);
      }
	  else{
		  model.put("deleteSucess",false);
	  }
	  
	  List<EditeurJeu> editeurs = editeurJeuService.getEditeurJeux();
	  model.put("editeurs", editeurs);
	  return "Editeur/GestionEditeur";
	}
	
}
