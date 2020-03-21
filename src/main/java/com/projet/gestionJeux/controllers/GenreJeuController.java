package com.projet.gestionJeux.controllers;

import java.util.List;

import com.projet.gestionJeux.models.GenreJeu;
import com.projet.gestionJeux.services.GenreJeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//@RestController
@Controller
//@RequestMapping("genres")
public class GenreJeuController {

	@Autowired
	private GenreJeuService genreJeuService;
	

	/**
	 * La liste des genres
	 * @return List<GenreJeu>
	 */
	@GetMapping("/test")
	public /*List<GenreJeu>*/ String getGenreJeux( ModelMap model) {
			//Iterable<GenreJeu> genreJeux = genreJeuService.getGenreJeux();
			//model.put("genreListe", genreJeux);
			return "genre-jeux";
			//return genreJeuService.getGenreJeux();
	}
	
	/**
	 * Créer un genre
	 */
	@PostMapping("/Ajoutgenre")
	public void creerOuModifierGenre(GenreJeu genreJeu) {
		this.genreJeuService.saveOrUpdate(genreJeu);
	}
	
	/**
	 * Supprimer un genre
	 */
	@DeleteMapping("/genres/{genreId}")
	public void supprimerGenre(int id) {
		this.genreJeuService.deleteGenreJeu(id);
	}

}
