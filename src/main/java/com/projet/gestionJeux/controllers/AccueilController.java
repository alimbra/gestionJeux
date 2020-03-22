package com.projet.gestionJeux.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.projet.gestionJeux.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.projet.gestionJeux.models.Jeu;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class AccueilController {

	@Autowired
	private JeuService jeuService;
	@Autowired
	private ThemeJeuService themeJeuService;

	@Autowired
	private TypeJeuService typeJeuService;

	@Autowired
	private GenreJeuService genreJeuService;

	@Autowired
	private EditeurJeuService editeurJeuService;


	/**
	 * 
	 * @param request
	 * @return La liste des jeux
	 */
	@GetMapping("/")
	public String gestionMeteos(HttpServletRequest request, ModelMap model) {

		List<Jeu> jeux = jeuService.findAll(); 

		model.put("jeux", jeux);

		model.put("typeJeux", typeJeuService.getTypeJeux());
		model.put("genreJeux", genreJeuService.getGenreJeux());
		model.put("themeJeux", themeJeuService.getThemeJeux());
		model.put("editeurJeux",editeurJeuService.getEditeurJeux());

		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request); // 1
	    if (!CollectionUtils.isEmpty(flashMap)) {
	    	if("deleteSucess" == (String)flashMap.get("typeMessage")) {
	    		model.put((String)flashMap.get("typeMessage"),((boolean) flashMap.get("typeMessagevalue"))); 
	    	}
	    	else model.put((String)flashMap.get("typeMessage"),jeuService.existeJeu((int) flashMap.get("jeuId"))); 
	    }

		return "Accueil/GestionJeux";
	}

	/**
	 * c'est pas propre ce que je fais ici
	 * Créer un genre
	 */
	@PostMapping("/acceuil/ajout-jeu")
	public String creerJeu(HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttributes) {
		String nom =  request.getParameter("nom");
		int type = Integer.parseInt(request.getParameter("type"));
		int theme =Integer.parseInt(request.getParameter("theme"));
		int genre = Integer.parseInt( request.getParameter("genre"));
		int editeur = Integer.parseInt( request.getParameter("editeur"));
		int nbmin = Integer.parseInt( request.getParameter("nbmin"));
		int nbmax = Integer.parseInt( request.getParameter("nbmax"));
		int age = Integer.parseInt( request.getParameter("agemin"));

		Jeu jeu =new Jeu();
		jeu.setNombre_joueurs_maximum(nbmax);
		jeu.setNom_jeu(nom);
		jeu.setNombre_joueurs_minimum(nbmin);
		jeu.setNombre_joueurs_maximum(nbmax);
		jeu.setAge_minimum(age);
		jeu.setEditeurJeu(editeurJeuService.findById(editeur));
		jeu.setThemeJeu(themeJeuService.findById(theme));
		jeu.setGenreJeu(genreJeuService.findById(genre));
		jeu.setTypeJeu(typeJeuService.findById(type));

		jeuService.saveOrUpdate(jeu);
		redirectAttributes.addFlashAttribute("jeuId", jeu.getId()); 
		redirectAttributes.addFlashAttribute("typeMessage", "addSucess"); 
    
    	return "redirect:/"; 
	}
	
	/**
	 * c'est pas propre ce que je fais ici
	 * Créer un genre
	 */
	@PostMapping("/acceuil/modifie-jeu")
	public String modifierJeu(HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttributes) {
		int id = Integer.parseInt(request.getParameter("jeuId"));
		String nom =  request.getParameter("nom");
		int type = Integer.parseInt(request.getParameter("type"));
		int theme =Integer.parseInt(request.getParameter("theme"));
		int genre = Integer.parseInt( request.getParameter("genre"));
		int editeur = Integer.parseInt( request.getParameter("editeur"));
		int nbmin = Integer.parseInt( request.getParameter("nbmin"));
		int nbmax = Integer.parseInt( request.getParameter("nbmax"));
		int age = Integer.parseInt( request.getParameter("agemin"));


		Jeu jeu =new Jeu();
		jeu.setId(id);
		jeu.setNombre_joueurs_maximum(nbmax);
		jeu.setNom_jeu(nom);
		jeu.setNombre_joueurs_minimum(nbmin);
		jeu.setNombre_joueurs_maximum(nbmax);
		jeu.setAge_minimum(age);
		jeu.setEditeurJeu(editeurJeuService.findById(editeur));
		jeu.setThemeJeu(themeJeuService.findById(theme));
		jeu.setGenreJeu(genreJeuService.findById(genre));
		jeu.setTypeJeu(typeJeuService.findById(type));

		jeuService.saveOrUpdate(jeu);

		jeuService.saveOrUpdate(jeu);
		redirectAttributes.addFlashAttribute("jeuId", jeu.getId()); 
		redirectAttributes.addFlashAttribute("typeMessage", "updateSucess"); 
    
    	return "redirect:/"; 
	}


	@GetMapping("/acceuil/supprimerJeu/{id}")
	public String supprimerJeu(@PathVariable( "id") Integer id, ModelMap model, RedirectAttributes redirectAttributes) {
		
		if (jeuService.existeJeu((int) id)) {
	  		this.jeuService.deleteJeuById((int)id);
	  		redirectAttributes.addFlashAttribute("typeMessagevalue", true); 
	    }
		else{
	      model.put("deleteSucess",false);
	      redirectAttributes.addFlashAttribute("typeMessagevalue", false); 
	    }
		
		redirectAttributes.addFlashAttribute("jeuId", id); 
		redirectAttributes.addFlashAttribute("typeMessage", "deleteSucess"); 
	
		return "redirect:/";
	}
	
}
