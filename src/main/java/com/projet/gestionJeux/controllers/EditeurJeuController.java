package com.projet.gestionJeux.controllers;

import com.projet.gestionJeux.models.EditeurJeu;
import com.projet.gestionJeux.models.GenreJeu;
import com.projet.gestionJeux.services.EditeurJeuService;
import com.projet.gestionJeux.services.JeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.List;
import java.util.Map;

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
		
		 Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request); // 1
		 if (!CollectionUtils.isEmpty(flashMap)) {
	    	if("deleteSucess" == (String)flashMap.get("typeMessage")) {
	    		model.put((String)flashMap.get("typeMessage"),((boolean) flashMap.get("typeMessagevalue"))); 
	    	}
	    	else model.put((String)flashMap.get("typeMessage"),editeurJeuService.existeEditeurJeu((int) flashMap.get("editorId"))); 
		 }
		    
		return "Editeur/GestionEditeur";
	}
	
	/**
	 * Créer un éditeur
	 */
	@PostMapping("/gestion-jeux/ajout-editeur")
	public String creerOuModifierEditeur(HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttributes) {
		String editeurNom = (String) request.getParameter("nom");
		System.out.println(editeurNom);
		EditeurJeu editeur = new EditeurJeu();
		editeur.setNom_editeur(editeurNom);
		EditeurJeu editeurJeu = this.editeurJeuService.saveOrUpdate(editeur);	

		redirectAttributes.addFlashAttribute("editorId", editeurJeu.getId()); 
		redirectAttributes.addFlashAttribute("typeMessage", "addSucess"); 
    
    	return "redirect:/gestion-jeux/editeurs"; 
		 
	}
	
	@PostMapping("/gestion-jeux/edit-editeur")
	public String editEditeur(HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttributes) {
		String editeur_id = request.getParameter("editKind_id");
		String editeurNom = (String) request.getParameter("nom");
		System.out.println(editeurNom);
		int editeurId = Integer.parseInt(editeur_id);
		EditeurJeu editeur = this.editeurJeuService.findById(editeurId);
		editeur.setNom_editeur(editeurNom); 
		EditeurJeu editeurJeu = this.editeurJeuService.saveOrUpdate(editeur);
		
		redirectAttributes.addFlashAttribute("editorId", editeurJeu.getId()); 
		redirectAttributes.addFlashAttribute("typeMessage", "updateSucess"); 
    
    	return "redirect:/gestion-jeux/editeurs"; 
		 
	}
	
	/**
	 * Supprimer un éditeur
	 * Un éditeur supprimé, supprimera tous les jeux associés à ce éditeur
	 */
	@GetMapping("/gestion-jeux/deleteEditeur/{id}")
	public String supprimerEditeur(@PathVariable("id") int id, ModelMap model, RedirectAttributes redirectAttributes)
	{
	  
	  	if (editeurJeuService.existeEditeurJeu(id)) {
	  		this.editeurJeuService.deleteEditeurJeu(id);
		  redirectAttributes.addFlashAttribute("typeMessagevalue", true); 
	    }
		else{
	      model.put("deleteSucess",false);
	      redirectAttributes.addFlashAttribute("typeMessagevalue", false); 
	    }
		
		redirectAttributes.addFlashAttribute("editorId", id); 
		redirectAttributes.addFlashAttribute("typeMessage", "deleteSucess"); 

		return "redirect:/gestion-jeux/editeurs";
	}
	
}
