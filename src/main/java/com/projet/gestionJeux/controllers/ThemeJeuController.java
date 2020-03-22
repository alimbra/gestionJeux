package com.projet.gestionJeux.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.projet.gestionJeux.models.ThemeJeu;
import com.projet.gestionJeux.services.ThemeJeuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;


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
		
		 Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request); // 1
	    if (!CollectionUtils.isEmpty(flashMap)) {
	    	if("deleteSucess" == (String)flashMap.get("typeMessage")) {
	    		model.put((String)flashMap.get("typeMessage"),((boolean) flashMap.get("typeMessagevalue"))); 
	    	}
	    	else model.put((String)flashMap.get("typeMessage"),themeJeuService.existeThemeJeu((int) flashMap.get("themeId"))); 
	    }
	    
		return "Theme/GestionTheme";
	}
	
	/**
	 * Créer un thème
	 */
	@PostMapping("/gestion-jeux/ajout-theme")
	public String creerOuModifierTheme(HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttributes) {
		String themeNom = (String) request.getParameter("nom");
		System.out.println(themeNom);
		ThemeJeu theme = new ThemeJeu();
		theme.setNom_theme(themeNom);
		ThemeJeu themeJeu = this.themeJeuService.saveOrUpdate(theme);
		
		redirectAttributes.addFlashAttribute("themeId", themeJeu.getId()); 
		redirectAttributes.addFlashAttribute("typeMessage", "addSucess"); 
    
    	return "redirect:/gestion-jeux/themes"; 
		 
	}
	
	@PostMapping("/gestion-jeux/edit-theme")
	public String editTheme(HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttributes) {
		String theme_id = request.getParameter("editKind_id");
		String themeNom = (String) request.getParameter("nom");
		System.out.println(themeNom);
		int themeId = Integer.parseInt(theme_id);
		ThemeJeu theme = this.themeJeuService.findById(themeId);
		theme.setNom_theme(themeNom); 
		ThemeJeu themeJeu = this.themeJeuService.saveOrUpdate(theme);
		
		redirectAttributes.addFlashAttribute("themeId", themeJeu.getId()); 
		redirectAttributes.addFlashAttribute("typeMessage", "updateSucess"); 
    
    	return "redirect:/gestion-jeux/themes"; 
		 
	}
	
	/**
	 * Supprimer un thème
	 */
	@GetMapping("/gestion-jeux/deleteTheme/{id}")
	public String supprimerTheme(@PathVariable("id") int id, ModelMap model, RedirectAttributes redirectAttributes)
	{
		
	  	if (themeJeuService.existeThemeJeu(id)) {
	  		this.themeJeuService.deleteThemeJeu(id);
		  redirectAttributes.addFlashAttribute("typeMessagevalue", true); 
	    }
		else{
	      model.put("deleteSucess",false);
	      redirectAttributes.addFlashAttribute("typeMessagevalue", false); 
	    }
		
		redirectAttributes.addFlashAttribute("themeId", id); 
		redirectAttributes.addFlashAttribute("typeMessage", "deleteSucess"); 
	
		return "redirect:/gestion-jeux/themes";
	}
}
