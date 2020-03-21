package com.projet.gestionJeux.controllers;

import java.util.List;

import com.projet.gestionJeux.models.ThemeJeu;
import com.projet.gestionJeux.services.ThemeJeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("themes")
public class ThemeJeuController {

	@Autowired
	private ThemeJeuService themeJeuService;
	

	/**
	 * La liste des thèmes
	 * @return List<ThemeJeu>
	 */
	@GetMapping("/themes")
	public List<ThemeJeu> getThemeJeux() {
		return themeJeuService.getThemeJeux();
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
