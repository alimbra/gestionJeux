package com.projet.gestionJeux.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	@GetMapping("/themes")
	public String getThemeJeux(HttpServletRequest request, ModelMap model) {
		List<ThemeJeu> themeJeux = themeJeuService.getThemeJeux();
		
		for (int i = 0; i < 20; i++) {
			ThemeJeu themeJeu = new ThemeJeu();
			themeJeu.setNom_theme("Theme " + i);
			themeJeux.add(themeJeu);
		}
		
		model.put("themeJeux", themeJeux);
		
		return "ThemesJeux";
	}
	
	/**
	 * Créer un thème
	 */
	@PostMapping("themes")
	public void creerOuModifierTheme(ThemeJeu themeJeu) {
		this.themeJeuService.saveOrUpdate(themeJeu);
	}
	
	/**
	 * Supprimer un thème
	 */
	@DeleteMapping("/themes/{themeId}")
	public void supprimerTheme(int id) {
		this.themeJeuService.deleteThemeJeu(id);
	}
}
