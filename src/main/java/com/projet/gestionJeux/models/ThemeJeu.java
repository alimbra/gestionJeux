package com.projet.gestionJeux.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "theme_jeu")
public class ThemeJeu {

  @Column
  @Id
  @GeneratedValue
  @JsonProperty("id")
  private int id;

  @Column
  @JsonProperty("nom_theme")
  private String nom_theme;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNom_theme() {
    return nom_theme;
  }

  public void setNom_theme(String nom_theme) {
    this.nom_theme = nom_theme;
  }
}

