package projet_tetris;

import java.io.Serializable;

public class Joueur implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  private String nom;
  private int niveau;
  private int score;
  
  
  public Joueur(String nom, int score, int niveau)
  {
	  this.nom = nom;
	  this.niveau = niveau;
	  this.score = score;
	    
  }

  
	public String getNom() {
		return nom;
	}
	
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public int getNiveau() {
		return niveau;
	}
	
	
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	
	public int getScore() {
		return score;
	}
	
	
	public void setScore(int score) {
		this.score = score;
	}
}