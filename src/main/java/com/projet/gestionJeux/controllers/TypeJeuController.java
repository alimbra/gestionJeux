package com.projet.gestionJeux.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.projet.gestionJeux.models.TypeJeu;
import com.projet.gestionJeux.services.TypeJeuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


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
		return "Type/GestionType";
	}
	
	/**
	 * Créer un type
	 */
	@PostMapping("/gestion-jeux/ajout-type")
	public String creerOuModifierType(HttpServletRequest request, ModelMap model) {
		String typeNom = (String) request.getParameter("nom");
		System.out.println(typeNom);
		TypeJeu type = new TypeJeu();
		type.setNom_type(typeNom);
		TypeJeu typeJeu = this.typeJeuService.saveOrUpdate(type);
		
		if (typeJeuService.existeTypeJeu(typeJeu.getId())){
			model.put("addSucess",true);
		}
		else{
			model.put("addSucess",false);
		}
		
		List<TypeJeu> types = typeJeuService.getTypeJeux();
		model.put("types", types);
		return "Type/GestionType";
		 
	}
	
	@PostMapping("/gestion-jeux/edit-type")
	public String editType(HttpServletRequest request, ModelMap model) {
		String type_id = request.getParameter("editKind_id");
		String typeNom = (String) request.getParameter("nom");
		System.out.println(typeNom);
		int typeId = Integer.parseInt(type_id);
		TypeJeu type = this.typeJeuService.findById(typeId);
		type.setNom_type(typeNom); 
		TypeJeu typeJeu = this.typeJeuService.saveOrUpdate(type);
		
		if (typeJeuService.existeTypeJeu(typeJeu.getId())){
			model.put("updateSucess",true);
		}
		else{
			model.put("updateSucess",false);
		}
		
		List<TypeJeu> types = typeJeuService.getTypeJeux();
		model.put("types", types);
		return "Type/GestionType";
		 
	}
	
	/**
	 * Supprimer un type
	 */
	@GetMapping("/gestion-jeux/deleteType/{id}")
	public String supprimerType(@PathVariable("id") int id, ModelMap model)
	{
	  if (typeJeuService.existeTypeJeu(id)) {
	      this.typeJeuService.deleteTypeJeu(id);
	      model.put("deleteSucess",true);
      }
	  else{
		  model.put("deleteSucess",false);
	  }
	  
	  List<TypeJeu> types = typeJeuService.getTypeJeux();
	  model.put("types", types);
	  return "Type/GestionType";
	}
}
