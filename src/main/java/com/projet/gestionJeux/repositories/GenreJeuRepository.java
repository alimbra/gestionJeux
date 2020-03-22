package com.projet.gestionJeux.repositories;

import com.projet.gestionJeux.models.GenreJeu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreJeuRepository extends JpaRepository<GenreJeu,Integer> {
	GenreJeu findById(int genreId);
}
