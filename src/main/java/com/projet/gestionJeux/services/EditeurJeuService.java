package com.projet.gestionJeux.services;

import com.projet.gestionJeux.models.EditeurJeu;
import com.projet.gestionJeux.repositories.EditeurJeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EditeurJeuService {

  @Autowired
  EditeurJeuRepository editeurJeuRepository;

  /**
   * Récuperer tous les editeurs de jeu.
   * @return liste des editeurs jeux.
   **/

  public List<EditeurJeu> getEditeurJeux() {
    List<EditeurJeu> editeurJeux = new ArrayList<>();
    editeurJeuRepository.findAll().forEach(editeurJeu -> editeurJeux.add(editeurJeu));
    return editeurJeux;
  }

  /**
   * Récuperer une entité d un editeur jeu.
   * @param id  de l´editeur.
   * @return une entite de type EditeurJeu.
   */
  public EditeurJeu getEditeurById(int id) {
    Optional<EditeurJeu> optionalEditeur = editeurJeuRepository.findById(id);
    if (optionalEditeur.isPresent()) {
      return optionalEditeur.get();
    }
    return null;
  }

  /**
   * Sauvegarder ou mettre à jour un editeurJeu.
   * @param editjeu une entite de type editeurJeu.
   */
  public void saveOrUpdate(EditeurJeu editjeu) {
    editeurJeuRepository.save(editjeu);
  }

  //A voir
  /**
   * La suppression d'un éditeur jeu supprimera tout les jeux associés
   * supprimer un editeurJeu.
   * @param id long: l´identifiant de la entité.
   */
  public void deleteEditeurJeu(int id) {
    editeurJeuRepository.deleteById(id);
  }
}
