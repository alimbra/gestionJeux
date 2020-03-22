package com.projet.gestionJeux.services;

import com.projet.gestionJeux.models.TypeJeu;
import com.projet.gestionJeux.repositories.TypeJeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeJeuService {
  @Autowired
  TypeJeuRepository typeJeuRepository;

  /**
   * Récuperer tous les Types de jeu.
   * @return liste des Types jeux.
   **/

  public List<TypeJeu> getTypeJeux() {
    List<TypeJeu> typeJeux = new ArrayList<>();
    typeJeuRepository.findAll().forEach(typeJeu -> typeJeux.add(typeJeu));
    return typeJeux;
  }

  /**
   * Récuperer une entité d un Type de jeu.
   * @param id  de Type jeu.
   * @return une entite de type TypeJeu.
   */
  	public TypeJeu findById(int typeId) {
		return this.typeJeuRepository.findById(typeId); 	
	}


  /**
   * Sauvegarder ou mettre à jour un typeJeu.
   * @param typeJeu une entite de type typeJeu.
   */
  public TypeJeu saveOrUpdate(TypeJeu typeJeu) {
    return typeJeuRepository.save(typeJeu);
  }

  /**
   * Supprimer un type ne supprime pas les jeux associé
   * supprimer un type Jeu .
   * @param id long: l´identifiant de la entité.
   */
  public void deleteTypeJeu(int id) {
    typeJeuRepository.deleteById(id);
  }
  
  public boolean existeTypeJeu(int id){
	  return typeJeuRepository.existsById(id);
  }
}
