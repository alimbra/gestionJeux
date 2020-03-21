package com.projet.gestionJeux.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "jeu")
public class Jeu {

  @Id
  @GeneratedValue
  @JsonProperty("id")
  private int id;

  @Column
  @JsonProperty("nom_jeu")
  String nom_jeu;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "id_type", referencedColumnName = "id")
  private  TypeJeu typeJeu;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "id_genre", referencedColumnName = "id")
  private  GenreJeu genreJeu;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "id_theme", referencedColumnName = "id")
  private  ThemeJeu themeJeu;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "id_editeur", referencedColumnName = "id")
  private  EditeurJeu editeurJeu;

  @Column
  @JsonProperty("age_minimum")
  private int age_minimum;

  @Column
  @JsonProperty("nombre_joueurs_minimum")
  private int nombre_joueurs_minimum;

  @Column
  @JsonProperty("nombre_joueurs_maximum")
  private int nombre_joueurs_maximum;

  public void setId(int id) {
    this.id = id;
  }

  public void setNom_jeu(String nom_jeu) {
    this.nom_jeu = nom_jeu;
  }

  public void setTypeJeu(TypeJeu typeJeu) {
    this.typeJeu = typeJeu;
  }

  public void setGenreJeu(GenreJeu genreJeu) {
    this.genreJeu = genreJeu;
  }

  public void setThemeJeu(ThemeJeu themeJeu) {
    this.themeJeu = themeJeu;
  }

  public void setEditeurJeu(EditeurJeu editeurJeu) {
    this.editeurJeu = editeurJeu;
  }

  public void setAge_minimum(int age_minimum) {
    this.age_minimum = age_minimum;
  }

  public void setNombre_joueurs_minimum(int nombre_joueurs_minimum) {
    this.nombre_joueurs_minimum = nombre_joueurs_minimum;
  }

  public void setNombre_joueurs_maximum(int nombre_joueurs_maximum) {
    this.nombre_joueurs_maximum = nombre_joueurs_maximum;
  }

  public long getId() {
    return id;
  }

  public String getNom_jeu() {
    return nom_jeu;
  }

  public TypeJeu getTypeJeu() {
    return typeJeu;
  }

  public GenreJeu getGenreJeu() {
    return genreJeu;
  }

  public ThemeJeu getThemeJeu() {
    return themeJeu;
  }

  public EditeurJeu getEditeurJeu() {
    return editeurJeu;
  }

  public int getAge_minimum() {
    return age_minimum;
  }

  public int getNombre_joueurs_minimum() {
    return nombre_joueurs_minimum;
  }

  public int getNombre_joueurs_maximum() {
    return nombre_joueurs_maximum;
  }
}
