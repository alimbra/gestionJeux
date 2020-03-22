package com.projet.gestionJeux.repositories;

import com.projet.gestionJeux.models.TypeJeu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeJeuRepository extends JpaRepository<TypeJeu,Integer> {
	TypeJeu findById(int typeId);
}
