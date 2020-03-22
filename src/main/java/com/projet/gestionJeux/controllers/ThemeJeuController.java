package com.projet.gestionJeux.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.projet.gestionJeux.models.GenreJeu;
import com.projet.gestionJeux.models.ThemeJeu;
import com.projet.gestionJeux.services.ThemeJeuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
		this.themeJeuService.saveOrUpdate(theme);
		
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
		this.themeJeuService.saveOrUpdate(theme);
		
		
		List<ThemeJeu> themes = themeJeuService.getThemeJeux();
		model.put("themes", themes);
		return "Theme/GestionTheme";
		 
	}
	
	/**
	 * Supprimer un thème
	 */
	@DeleteMapping("/themes/{themeId}")
	public void supprimerTheme(int id) {
		this.themeJeuService.deleteThemeJeu(id);
	}
}
