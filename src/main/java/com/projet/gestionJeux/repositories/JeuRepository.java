package com.projet.gestionJeux.repositories;


import java.util.List;

import com.projet.gestionJeux.models.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JeuRepository extends JpaRepository<Jeu,Integer> {

	List<Jeu> findAll();

	Jeu findById(int id);

	@Query(value = "DELETE FROM jeu WHERE id_editeur = ?", nativeQuery = true)
	void deleteByIdEditeur(int idEditeur);


	@Query(value = "SELECT * FROM jeu WHERE id_type = ?1 AND id_genre = ?2 AND id_theme = ?3 AND nombre_joueurs_minimum = ?4"
			+ "AND nombre_joueurs_maximum = ?5 AND age_minimum = ?6 AND id_editeur = ?7", nativeQuery = true)
	List<Jeu> findByTypeGenreThemeNbJMinNbJMaxAgeMinEditeur(int idType,
																													int idGenre,
																													int idTheme,
																													int nbJMin,
																													int nbJMax,
																													int ageMin,
																													int idEditeur);

}
