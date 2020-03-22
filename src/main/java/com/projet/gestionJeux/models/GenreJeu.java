package com.projet.gestionJeux.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "genre_jeu")
public class GenreJeu {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  @JsonProperty("id")
  private int id;

  @Column
  @JsonProperty("nom")
  private String nom_genre;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNom_genre() {
    return nom_genre;
  }

  public void setNom_genre(String nom_genre) {
    this.nom_genre = nom_genre;
  }
}
