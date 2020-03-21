package com.projet.gestionJeux.services;

import com.projet.gestionJeux.models.TypeJeu;
import com.projet.gestionJeux.repositories.TypeJeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
  public TypeJeu getTypeById(int id) {
    Optional<TypeJeu> optionalTypeJeu = typeJeuRepository.findById(id);
    if (optionalTypeJeu.isPresent()) {
      return optionalTypeJeu.get();
    }
    return null;
  }

  /**
   * Sauvegarder ou mettre à jour un typeJeu.
   * @param typeJeu une entite de type typeJeu.
   */
  public void saveOrUpdate(TypeJeu typeJeu) {
    typeJeuRepository.save(typeJeu);
  }

  /**
   * Supprimer un type ne supprime pas les jeux associé
   * supprimer un type Jeu .
   * @param id long: l´identifiant de la entité.
   */
  public void deleteTypeJeu(int id) {
    typeJeuRepository.deleteById(id);
  }
}
