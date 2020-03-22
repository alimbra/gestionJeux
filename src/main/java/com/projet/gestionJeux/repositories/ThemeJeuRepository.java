package com.projet.gestionJeux.repositories;

import com.projet.gestionJeux.models.ThemeJeu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeJeuRepository extends JpaRepository<ThemeJeu,Integer> {
	ThemeJeu findById(int themeId);
}
