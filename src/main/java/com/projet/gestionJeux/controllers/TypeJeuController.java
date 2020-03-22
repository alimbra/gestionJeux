package com.projet.gestionJeux.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.projet.gestionJeux.models.TypeJeu;
import com.projet.gestionJeux.services.TypeJeuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;


@Controller
public class TypeJeuController {

	//@Autowired
 	private final TypeJeuService typeJeuService;
	
	public TypeJeuController(TypeJeuService typeJeuService) {
		this.typeJeuService = typeJeuService;
	}
	

	/**
	 * La liste des types
	 * @return la liste des types de jeu
	 */
	@GetMapping("/gestion-jeux/types")
	public String getTypeJeux(HttpServletRequest request, ModelMap model) {
		List<TypeJeu> typeJeux = typeJeuService.getTypeJeux();
		model.put("types", typeJeux);
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request); // 1
	    if (!CollectionUtils.isEmpty(flashMap)) {
	    	if("deleteSucess" == (String)flashMap.get("typeMessage")) {
	    		model.put((String)flashMap.get("typeMessage"),((boolean) flashMap.get("typeMessagevalue"))); 
	    	}
	    	else model.put((String)flashMap.get("typeMessage"), typeJeuService.existeTypeJeu((int) flashMap.get("typeId"))); 
	    }
		return "Type/GestionType";
	}
	
	/**
	 * Créer un type
	 */
	@PostMapping("/gestion-jeux/ajout-type")
	public String creerOuModifierType(HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttributes) {
		String typeNom = (String) request.getParameter("nom");
		System.out.println(typeNom);
		TypeJeu type = new TypeJeu();
		type.setNom_type(typeNom);
		TypeJeu typeJeu = this.typeJeuService.saveOrUpdate(type);
		
		redirectAttributes.addFlashAttribute("typeId", typeJeu.getId()); 
		redirectAttributes.addFlashAttribute("typeMessage", "addSucess"); 
    
    	return "redirect:/gestion-jeux/types"; 
		 
	}
	
	@PostMapping("/gestion-jeux/edit-type")
	public String editType(HttpServletRequest request, ModelMap model,  RedirectAttributes redirectAttributes) {
		String type_id = request.getParameter("editKind_id");
		String typeNom = (String) request.getParameter("nom");
		System.out.println(typeNom);
		int typeId = Integer.parseInt(type_id);
		TypeJeu type = this.typeJeuService.findById(typeId);
		type.setNom_type(typeNom); 
		TypeJeu typeJeu = this.typeJeuService.saveOrUpdate(type);
		
		redirectAttributes.addFlashAttribute("typeId", typeJeu.getId()); 
		redirectAttributes.addFlashAttribute("typeMessage", "updateSucess"); 
    
    	return "redirect:/gestion-jeux/types"; 
		 
	}
	
	/**
	 * Supprimer un type
	 */
	@GetMapping("/gestion-jeux/deleteType/{id}")
	public String supprimerType(@PathVariable("id") int id, ModelMap model,  RedirectAttributes redirectAttributes)
	{
		
	  	if (typeJeuService.existeTypeJeu(id)) {
	  		this.typeJeuService.deleteTypeJeu(id);
	  		redirectAttributes.addFlashAttribute("typeMessagevalue", true); 
	    }
		else{
	      model.put("deleteSucess",false);
	      redirectAttributes.addFlashAttribute("typeMessagevalue", false); 
	    }
		
		redirectAttributes.addFlashAttribute("typeId", id); 
		redirectAttributes.addFlashAttribute("typeMessage", "deleteSucess"); 

		return "redirect:/gestion-jeux/types";
	}
}
