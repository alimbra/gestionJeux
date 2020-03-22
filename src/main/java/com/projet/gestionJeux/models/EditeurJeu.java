package com.projet.gestionJeux.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "editeur_jeu")
public class EditeurJeu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	@JsonProperty("id")
	private int id;
	
	@Column
	@JsonProperty("nom_editeur")
	private String nom_editeur;
	
	/*public EditeurJeu(String nom_editeur) {
		this.nom_editeur = nom_editeur;
	}*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom_editeur() {
		return nom_editeur;
	}

	public void setNom_editeur(String nom_editeur) {
		this.nom_editeur = nom_editeur;
	}

}
