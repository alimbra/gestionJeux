package com.projet.gestionJeux.services;

import com.projet.gestionJeux.models.GenreJeu;
import com.projet.gestionJeux.repositories.GenreJeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class GenreJeuService {

  @Autowired
  GenreJeuRepository genreJeuRepository;

  /**
   * Récuperer tous les genres de jeu.
   * @return liste des genres jeux.
   **/

  public List<GenreJeu> getGenreJeux() {
    List<GenreJeu> genreJeux = new ArrayList<>();
    genreJeuRepository.findAll().forEach(genreJeu -> genreJeux.add(genreJeu));
    return genreJeux;
  }

  /**
   * Récuperer une entité d'un genre de jeu.
   * @param id  de genre jeu.
   * @return une entite de type GenreJeu.
   */
 /* public GenreJeu getGenreById(int id) {
    Optional<GenreJeu> optionalGenre = genreJeuRepository.findById(id);
    if (optionalGenre.isPresent()) {
      return optionalGenre.get();
    }
    return null;
  }*/

  /**
   * Sauvegarder ou mettre à jour un genreJeu.
   * @param genreJeu une entite de type GenreJeu.
   */
  public GenreJeu saveOrUpdate(GenreJeu genreJeu) {
    return genreJeuRepository.save(genreJeu);
  }

  /**
   * Supprimer un genre ne supprime pas les jeux associé
   * supprimer un genre Jeu .
   * @param id long: l´identifiant de la entité.
   */
  public void deleteGenreJeu(int id) {
    genreJeuRepository.deleteById(id);
  }
  public boolean existeGenreJei(int id){
    return genreJeuRepository.existsById(id);
  }
	public GenreJeu findById(int genreId) {
		return this.genreJeuRepository.findById(genreId); 	
	}

}
