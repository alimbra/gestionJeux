package com.projet.gestionJeux.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.projet.gestionJeux.models.ThemeJeu;
import com.projet.gestionJeux.services.ThemeJeuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ThemeJeuController {

	//@Autowired
	private final ThemeJeuService themeJeuService;
	
	public ThemeJeuController(ThemeJeuService themeJeuService) {
		this.themeJeuService = themeJeuService;
	}

	/**
	 * La liste des types
	 * @return la liste des types de jeu
	 */
	@GetMapping("/gestion-jeux/themes")
	public String getThemeJeux(HttpServletRequest request, ModelMap model) {
		List<ThemeJeu> themeJeux = themeJeuService.getThemeJeux();
		model.put("themes", themeJeux);
		return "Theme/GestionTheme";
	}
	
	/**
	 * Créer un thème
	 */
	@PostMapping("/gestion-jeux/ajout-theme")
	public String creerOuModifierTheme(HttpServletRequest request, ModelMap model) {
		String themeNom = (String) request.getParameter("nom");
		System.out.println(themeNom);
		ThemeJeu theme = new ThemeJeu();
		theme.setNom_theme(themeNom);
		ThemeJeu themeJeu = this.themeJeuService.saveOrUpdate(theme);
		
		if (themeJeuService.existeThemeJeu(themeJeu.getId())){
			model.put("addSucess",true);
		}
		else{
			model.put("addSucess",false);
		}
		
		List<ThemeJeu> themes = themeJeuService.getThemeJeux();
		model.put("themes", themes);
		return "Theme/GestionTheme";
		 
	}
	
	@PostMapping("/gestion-jeux/edit-theme")
	public String editTheme(HttpServletRequest request, ModelMap model) {
		String theme_id = request.getParameter("editKind_id");
		String themeNom = (String) request.getParameter("nom");
		System.out.println(themeNom);
		int themeId = Integer.parseInt(theme_id);
		ThemeJeu theme = this.themeJeuService.findById(themeId);
		theme.setNom_theme(themeNom); 
		ThemeJeu themeJeu = this.themeJeuService.saveOrUpdate(theme);
		
		if (themeJeuService.existeThemeJeu(themeJeu.getId())){
			model.put("updateSucess",true);
		}
		else{
			model.put("updateSucess",false);
		}
		
		
		List<ThemeJeu> themes = themeJeuService.getThemeJeux();
		model.put("themes", themes);
		return "Theme/GestionTheme";
		 
	}
	
	/**
	 * Supprimer un thème
	 */
	@GetMapping("/gestion-jeux/deleteTheme/{id}")
	public String supprimerTheme(@PathVariable("id") int id, ModelMap model)
	{
	  if (themeJeuService.existeThemeJeu(id)) {
	      this.themeJeuService.deleteThemeJeu(id);
	      model.put("deleteSucess",true);
      }
	  else{
		  model.put("deleteSucess",false);
	  }
	  
	  List<ThemeJeu> themes = themeJeuService.getThemeJeux();
	  model.put("themes", themes);
	  return "Theme/GestionTheme";
	}
}
