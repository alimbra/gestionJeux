package com.projet.gestionJeux.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.projet.gestionJeux.models.GenreJeu;
import com.projet.gestionJeux.services.GenreJeuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class GenreJeuController {

	@Autowired
	private GenreJeuService genreJeuService;
	

	/**
	 * La liste des genres
	 * @return List<GenreJeu>
	 */
	@GetMapping("/gestion-jeux/genres")
	public String getGenreJeux( ModelMap model, HttpServletRequest request) {
		List<GenreJeu> genres = genreJeuService.getGenreJeux();
		model.put("genres", genres);
		
	    Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request); // 1
	    if (!CollectionUtils.isEmpty(flashMap)) {
	    	if("deleteSucess" == (String)flashMap.get("typeMessage")) {
	    		model.put((String)flashMap.get("typeMessage"),((boolean) flashMap.get("typeMessagevalue"))); 
	    	}
	    	else model.put((String)flashMap.get("typeMessage"),genreJeuService.existeGenreJei((int) flashMap.get("kindId"))); 
	    }

	    return "Genre/GestionGenre";
	}
	
	/**
	 * c'est pas propre ce que je fais ici
	 * Créer un genre
	 */
	@PostMapping("/gestion-jeux/ajout-genre")
	public String creerOuModifierGenre(HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttributes) {
		String genreNom = (String) request.getParameter("nom");
		GenreJeu genre = new GenreJeu();
		genre.setNom_genre(genreNom);

		//rajout et recupere le genre du jeu inséré
		GenreJeu genreJeu = this.genreJeuService.saveOrUpdate(genre);
		redirectAttributes.addFlashAttribute("kindId", genre.getId()); 
		redirectAttributes.addFlashAttribute("typeMessage", "addSucess"); 
    
    	return "redirect:/gestion-jeux/genres"; 
		 
	}
	
	@PostMapping("/gestion-jeux/edit-genre")
	public String editGenre(HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttributes) {
		String genre_id = request.getParameter("editKind_id");
		String genreNom = (String) request.getParameter("nom");
		System.out.println(genreNom);
		int genreId = Integer.parseInt(genre_id);
		GenreJeu genre = this.genreJeuService.findById(genreId);
		genre.setNom_genre(genreNom); 

		GenreJeu genreJeu = this.genreJeuService.saveOrUpdate(genre);
		redirectAttributes.addFlashAttribute("kindId", genre.getId()); 
		redirectAttributes.addFlashAttribute("typeMessage", "updateSucess"); 
    	
    	return "redirect:/gestion-jeux/genres"; 
	}
	
	
	/**
	 * Supprimer un genre
	 */
	@GetMapping("/gestion-jeux/deleteGenre/{id}")
	public String supprimerGenre(@PathVariable("id") int id, ModelMap model, RedirectAttributes redirectAttributes)
	{
		if (genreJeuService.existeGenreJei(id)) {
	      this.genreJeuService.deleteGenreJeu(id);
		  redirectAttributes.addFlashAttribute("typeMessagevalue", true); 
	    }
		else{
	      model.put("deleteSucess",false);
	      redirectAttributes.addFlashAttribute("typeMessagevalue", false); 
	    }
		
		redirectAttributes.addFlashAttribute("kindId", id); 
		redirectAttributes.addFlashAttribute("typeMessage", "deleteSucess"); 

		return "redirect:/gestion-jeux/genres";
	}

}
