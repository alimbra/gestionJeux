package com.projet.gestionJeux.services;

import com.projet.gestionJeux.models.Jeu;
import com.projet.gestionJeux.models.NoteJeu;
import com.projet.gestionJeux.repositories.JeuRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JeuService {

  @Autowired
  JeuRepository jeuRepository;
  
  /**
   * Recupèrer les jeux correspondant aux filtres sur :
   * 	- type
   * 	- genre
   * 	- theme
   * 	- nombre de joueurs (max et min)
   * 	- age minimum conseillé
   * 	- editeur
   * 
   * Afficher ces jeux avec leurs notes triés par ordre décroissant
   * 
   */
  public List<Jeu> getJeuxByFiltre(int idType, int idGenre, int idTheme, int nbJMin, int nbJMax, int ageMin, int idEditeur) {
	  return jeuRepository.findByTypeGenreThemeNbJMinNbJMaxAgeMinEditeur(idType, idGenre, idTheme, nbJMin, nbJMax, ageMin, idEditeur);
  }
  

  /**
   * Supprimer les jeux associés à un éditeur
   */
  public void deleteJeuxByIdEditeur(int idEditeur) {
	  jeuRepository.deleteByIdEditeur(idEditeur);
  }


  public Jeu findById(int id){
    return jeuRepository.findById(id);
  }
  public Jeu saveOrUpdate(Jeu jeu){
    return jeuRepository.save(jeu);
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
	public List<Jeu> findAll() {
		return this.jeuRepository.findAll();
	}

  public void deleteJeuById(int id) {
    jeuRepository.deleteById(id);
  }


   public boolean existeJeu(int id) {
	   return this.jeuRepository.existsById(id);
   }
}
