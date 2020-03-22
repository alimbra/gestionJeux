package com.projet.gestionJeux.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "note_jeu")
public class NoteJeu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  @JsonProperty("id")
  private int id;

  @Column
  @JsonProperty("note")
  private int note;

  @Column
  @JsonProperty("nom_testeur")
  private String nom_testeur;


  @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  @JoinColumn(name = "id_jeu", nullable = false)
  private Jeu jeu;



  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getNote() {
    return note;
  }

  public void setNote(int note) {
    this.note = note;
  }

  public String getNom_testeur() {
    return nom_testeur;
  }

  public void setNom_testeur(String nom_testeur) {
    this.nom_testeur = nom_testeur;
  }

  public Jeu getJeu() {
    return jeu;
  }

  public void setJeu(Jeu jeu) {
    this.jeu = jeu;
  }

}
