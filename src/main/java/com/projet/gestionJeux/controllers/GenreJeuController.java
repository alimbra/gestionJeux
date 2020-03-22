package com.projet.gestionJeux.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.projet.gestionJeux.models.GenreJeu;
import com.projet.gestionJeux.services.GenreJeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class GenreJeuController {

	@Autowired
	private GenreJeuService genreJeuService;
	

	/**
	 * La liste des genres
	 * @return List<GenreJeu>
	 */
	@GetMapping("/gestion-jeux/genres")
	public String getGenreJeux( ModelMap model) {
			List<GenreJeu> genres = genreJeuService.getGenreJeux();
			model.put("genres", genres);
			return "Genre/GestionGenre";
	}
	
	/**
	 * c'est pas propre ce que je fais ici
	 * Créer un genre
	 */
	@PostMapping("/gestion-jeux/ajout-genre")
	public String creerOuModifierGenre(HttpServletRequest request, ModelMap model) {
		
		String genreNom = (String) request.getParameter("nom");
		System.out.println(genreNom);
		GenreJeu genre = new GenreJeu();
		genre.setNom_genre(genreNom);
		this.genreJeuService.saveOrUpdate(genre);
		
		List<GenreJeu> genres = genreJeuService.getGenreJeux();
		model.put("genres", genres);
		return "Genre/GestionGenre";
		 
	}
	
	@PostMapping("/gestion-jeux/edit-genre")
	public String editGenre(HttpServletRequest request, ModelMap model) {
		String genre_id = request.getParameter("editKind_id");
		String genreNom = (String) request.getParameter("nom");
		System.out.println(genreNom);
		int genreId = Integer.parseInt(genre_id);
		GenreJeu genre = this.genreJeuService.findById(genreId);
		genre.setNom_genre(genreNom); 
		this.genreJeuService.saveOrUpdate(genre);
		
		
		List<GenreJeu> genres = genreJeuService.getGenreJeux();
		model.put("genres", genres);
		return "Genre/GestionGenre";
		 
	}
	
	
	/**
	 * Supprimer un genre
	 */
	@DeleteMapping("/genres/{genreId}")
	public void supprimerGenre(int id) {
		this.genreJeuService.deleteGenreJeu(id);
	}

}
