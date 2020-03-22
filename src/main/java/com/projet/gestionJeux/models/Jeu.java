package com.projet.gestionJeux.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jeu")
public class Jeu {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  @JsonProperty("id")
  private int id;


  @Column
  @JsonProperty("nom_jeu")
  String nom_jeu;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, orphanRemoval = false)
  @JoinColumn(name = "id_type", referencedColumnName = "id")
  private  TypeJeu typeJeu;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, orphanRemoval = false)
  @JoinColumn(name = "id_genre", referencedColumnName = "id")
  private  GenreJeu genreJeu;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, orphanRemoval = false)
  @JoinColumn(name = "id_theme", referencedColumnName = "id")
  private  ThemeJeu themeJeu;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, orphanRemoval = false)
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

  @Transient
  private double calculFinal;

  public List<NoteJeu> getNoteJeux() {
    return noteJeux;
  }

  public void setNoteJeux(List<NoteJeu> noteJeux) {
    this.noteJeux = noteJeux;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "jeu")
  private List<NoteJeu> noteJeux;

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

  public int getId() {
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

  public double getCalculFinal() {
    calculFinal = calculfinal(this,0,0,this.noteJeux);
    return calculFinal;
  }
  /**
   * fait des comparaison par rapport au nom et au operateur
   * @param noteJeu
   * @param nomTesteur
   * @param operateur
   * @param limite
   * @return
   */
  private static boolean verifieNote(NoteJeu noteJeu, String nomTesteur, String operateur, int limite){
    boolean resultNum = false;
    switch (operateur){
      case "<=":
        resultNum = resultNum || noteJeu.getNote()<=limite;

      case "<":
        resultNum = resultNum || noteJeu.getNote()<=limite;
      case ">":

        resultNum = resultNum || noteJeu.getNote()>limite;
      case ">=":
        resultNum = resultNum || noteJeu.getNote()<=limite;
    }
    boolean resultNom = false;
    resultNom = resultNom || noteJeu.getNom_testeur()==nomTesteur;
    return resultNum && resultNom;
  }

  /**
   * fonction de calcul redefini
   * @param jeu
   * @param numerateur
   * @param denominateur le denominateur
   * @param noteJeux liste de note de jeux
   * @return
   */

  public static double calculfinal(Jeu jeu, int numerateur, int denominateur, List<NoteJeu> noteJeux) {
    String theme = jeu.getThemeJeu().getNom_theme();
    String genre = jeu.getGenreJeu().getNom_genre();
    String type = jeu.getTypeJeu().getNom_type();
    String editeur = jeu.getEditeurJeu().getNom_editeur();
    int llI = 1; --denominateur;
    if (noteJeux.size()== 1) {
      NoteJeu noteJeu = noteJeux.get(0);
      numerateur += noteJeu.getNote();
      if (theme == "sciencefiction" && verifieNote( noteJeu,"Didier Loyal","<=",9)){
        ++numerateur;
        llI = 2;
      }
      if (genre == "gestion" && verifieNote( noteJeu,"Armande Moly","<",10)) {
        numerateur++;
      }
      if (type == "jeu de cartes" && verifieNote( noteJeu,"Gaston Portaleau",">",0)) {
        numerateur -= 1;
      }
      if (editeur == "édijeu" && verifieNote(noteJeu,"Liz Smallhead",">=",2)) {
        numerateur = 2;
        llI += 4;
      }
      if (theme == "contemporain" &&
              verifieNote(noteJeu,"Stefan Bergdorf",">=",3)
              &&  verifieNote(noteJeu,"Stefan Bergdorf","<=",7)
      ) {
        numerateur = numerateur + 1 / 2;
      }
      denominateur += 2;
      return (double) numerateur / (Double)(double) denominateur;
    }
    if (noteJeux.size() == 2) {
      if (llI > 1) {
        NoteJeu noteJeu1 = noteJeux.get(0);
        NoteJeu noteJeu2 = noteJeux.get(1);

        numerateur += noteJeu1.getNote() + noteJeu2.getNote();
        denominateur += 3;
        if (theme == "sciencefiction"
                && verifieNote( noteJeu1,"Didier Loyal","<=",9)
                && verifieNote( noteJeu2,"Didier Loyal","<=",9)
        ) {
          ++numerateur;
          llI = 2;
        }
        if (genre == "gestion"
                && verifieNote( noteJeu1,"Armande Moly","<",10)
                && verifieNote( noteJeu2,"Armande Moly","<",10)
        ) {
          numerateur++;
        }
        if (type == "jeu de cartes"
                && verifieNote( noteJeu1,"Gaston Portaleau",">",0)
                && verifieNote( noteJeu2,"Gaston Portaleau",">",0)
        ) {
          numerateur -= 1;
        }
        if (editeur == "édijeu"
                && verifieNote( noteJeu1,"Liz Smallhead",">=",2)
                && verifieNote( noteJeu2,"Liz Smallhead",">=",2)){

          numerateur -= 2;
          llI += 4;
        }
        if (theme == "contemporain"
                && verifieNote(noteJeu1,"Stefan Bergdorf",">=",3)
                && verifieNote(noteJeu1,"Stefan Bergdorf",">=",3)
                &&  verifieNote(noteJeu2,"Stefan Bergdorf","<=",7)
                &&  verifieNote(noteJeu2,"Stefan Bergdorf","<=",7)
        ) {
          numerateur = numerateur + 1 / 2;
        }
        return (double) numerateur / (double) denominateur;
      }
    }
    if (noteJeux.size()>=2) {
      NoteJeu noteJeu = noteJeux.get(0);
      numerateur += noteJeu.getNote();
      denominateur += 2;
      List<NoteJeu> newlI1 = new ArrayList<NoteJeu>();
      newlI1.add(noteJeux.get(noteJeux.size()-1));
      if (theme == "sciencefiction" && verifieNote( noteJeu,"Didier Loyal","<=",9)){
        ++numerateur;
        llI = 2;
      }
      if (genre == "gestion" && verifieNote( noteJeu,"Armande Moly","<",10)) {
        numerateur++;
      }
      if (type == "jeu de cartes" && verifieNote( noteJeu,"Gaston Portaleau",">",0)) {
        numerateur -= 1;
      }
      if (editeur == "édijeu" && verifieNote(noteJeu,"Liz Smallhead",">=",2)) {
        numerateur = 2;
        llI += 4;
      }
      if (theme == "contemporain" &&
              verifieNote(noteJeu,"Stefan Bergdorf",">=",3)
              &&  verifieNote(noteJeu,"Stefan Bergdorf","<=",7)
      ) {
        numerateur = numerateur + 1 / 2;
      }
      System.arraycopy(noteJeux, 2, newlI1, 0, noteJeux.size()-1);
      return calculfinal(jeu, numerateur, denominateur, newlI1);
    }
    if (noteJeux.size() == 0) {
      return 0.0;
    }
    return - 1.0;
  }

}
