package com.projet.gestionJeux.services;

import com.projet.gestionJeux.models.ThemeJeu;
import com.projet.gestionJeux.repositories.ThemeJeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThemeJeuService {

  @Autowired
  ThemeJeuRepository themeJeuRepository;

  /**
   * Récuperer tous les themes de jeu.
   * @return liste des themes jeux.
   **/

  public List<ThemeJeu> getThemeJeux() {
    List<ThemeJeu> themeJeux = new ArrayList<>();
    themeJeuRepository.findAll().forEach(themeJeu -> themeJeux.add(themeJeu));
    return themeJeux;
  }

  /**
   * Récuperer une entité d un theme de jeu.
   * @param id  de theme jeu.
   * @return une entite de type ThemeJeu.
   */
  public ThemeJeu findById(int themeId) {
		return this.themeJeuRepository.findById(themeId); 	
  }


  /**
   * Sauvegarder ou mettre à jour un themeJeu.
   * @param themeJeu une entite de type themeJeu.
   */
  public ThemeJeu saveOrUpdate(ThemeJeu themeJeu) {
    return themeJeuRepository.save(themeJeu);
  }

  /**
   * Supprimer un theme ne supprime pas les jeux associé
   * supprimer un theme Jeu .
   * @param id long: l´identifiant de la entité.
   */
  public void deleteThemeJeu(int id) {
    themeJeuRepository.deleteById(id);
  }
  
  public boolean existeThemeJeu(int id){
	  return themeJeuRepository.existsById(id);
  }


}
