package projet_tetris;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;


/**
 * <p>Titre : PanneauScore</p>
 * <p>Description : Classe du panneau affichant les meilleurs scores </p>
 * <p>Copyright : Copyright (c) 2004</p>
 * <p>Société : </p>
 * @author Cédric Montillot et Djilali Maghraoui
 * @version 1.0
 */


public class PanneauScore  extends JDialog
{
  Vector<Joueur> resultat=null;
// Nombre de joueurs classés dans le panneau des meilleurs scores
  int NbJoueurs = 5;
  // Tableau contenant le nom des 5 meilleurs joueurs
  String BestJoueurs[];
  // Tableau contenant le score et le niveau atteint par les 5 meilleurs
  // joueurs
  int Attributs[][];
  // Composants de l'interface
  GridBagLayout gridBagLayoutScore = new GridBagLayout();
  JButton BoutonOK = new JButton();
  // Tableau de composants JLabel pour afficher la position des joueurs
  JLabel [] LabelPosition = new JLabel[NbJoueurs];
  // Tableau de composants JLabel pour afficher le nom des joueurs
  JLabel [] LabelNom = new JLabel[NbJoueurs];
  // Tableau de composants JLabel pour afficher le niveau atteint par
  // les joueurs
  JLabel [] LabelNiveau = new JLabel[NbJoueurs];
  // Tableau de composants JLabel pour afficher le score des joueurs
  JLabel [] LabelScore = new JLabel[NbJoueurs];
  // Composant JLabel permettant d'afficher "Position"
  JLabel LabelTxtPosition = new JLabel();
  // Composant JLabel permettant d'afficher "Nom"
  JLabel LabelTxtNom = new JLabel();
  // Composant JLabel permettant d'afficher "Niveau"
  JLabel LabelTxtNiveau = new JLabel();
  // Composant JLabel permettant d'afficher "Score"
  JLabel LabelTxtScore = new JLabel();



  // Constructeur de la classe
  public PanneauScore()
  {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }



  private void jbInit() throws Exception {
    // Variable utilisée pour fixer les scores par défaut
    int Score = 0;
    // Initialisation du tableau contenant le nom des 5 meileurs joueurs
    BestJoueurs = new String[NbJoueurs];
    for(int i=0;i<NbJoueurs;i++)
      BestJoueurs[i] = "Ordinateur";
    // Initialisation du tableau des attributs
    Attributs = new int[NbJoueurs][2];
    for(int i=0;i<NbJoueurs;i++)
    {
      Attributs[i][0] = 3;
      Attributs[i][1] = Score;
    }
    
    // Initialisation des composants
    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    this.setResizable(false);
    this.setTitle("Les Meilleurs Scores");
    BoutonOK.setText("OK");
    BoutonOK.addActionListener(new PanneauScore_BoutonOK_actionAdapter(this));
    this.getContentPane().setLayout(gridBagLayoutScore);
    LabelTxtPosition.setFont(new java.awt.Font("Dialog", 1, 11));
    LabelTxtPosition.setForeground(Color.darkGray);
    LabelTxtPosition.setVerifyInputWhenFocusTarget(true);
    LabelTxtPosition.setText("Position");
    LabelTxtNom.setFont(new java.awt.Font("Dialog", 1, 11));
    LabelTxtNom.setHorizontalAlignment(SwingConstants.CENTER);
    LabelTxtNom.setHorizontalTextPosition(SwingConstants.CENTER);
    LabelTxtNom.setText("Nom");
    LabelTxtNiveau.setFont(new java.awt.Font("Dialog", 1, 11));
    LabelTxtNiveau.setHorizontalAlignment(SwingConstants.CENTER);
    LabelTxtNiveau.setHorizontalTextPosition(SwingConstants.CENTER);
    LabelTxtNiveau.setText("Niveau");
    LabelTxtScore.setFont(new java.awt.Font("Dialog", 1, 11));
    LabelTxtScore.setHorizontalAlignment(SwingConstants.CENTER);
    LabelTxtScore.setHorizontalTextPosition(SwingConstants.CENTER);
    LabelTxtScore.setText("Score");
    int Pos = 1;
    for(int i=0;i<NbJoueurs;i++)
    {
      LabelPosition[i] = new JLabel();
      LabelPosition[i].setFont(new java.awt.Font("Dialog", 1, 11));
      LabelPosition[i].setForeground(SystemColor.menuText);
      LabelPosition[i].setHorizontalAlignment(SwingConstants.CENTER);
      LabelPosition[i].setText(Pos+"-");
      Pos++;
    }
    this.getContentPane().add(LabelTxtPosition, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 12));
    this.getContentPane().add(LabelTxtNom, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 39, 0, 45), 0, 0));
    this.getContentPane().add(LabelTxtNiveau, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 15, 0, 16), 0, 0));
    this.getContentPane().add(LabelTxtScore, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 33, 0, 42), 0, 0));
    for(int i=0;i<NbJoueurs;i++)
    {
      this.getContentPane().add(LabelPosition[i],     new GridBagConstraints(0, i+1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

      LabelNom[i] = new JLabel();
      LabelNom[i].setText(BestJoueurs[i]);
      this.getContentPane().add(LabelNom[i],     new GridBagConstraints(1, i+1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

      LabelNiveau[i] = new JLabel();
      LabelNiveau[i].setText(Attributs[i][0]+"");
      this.getContentPane().add(LabelNiveau[i],     new GridBagConstraints(2, i+1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

      LabelScore[i] = new JLabel();
      LabelScore[i].setText(Attributs[i][1]+"");
      this.getContentPane().add(LabelScore[i],     new GridBagConstraints(3, i+1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    }
    this.getContentPane().add(BoutonOK, new GridBagConstraints(1, 6, 3, 1, 0.0, 0.0
            ,GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 67, 0, 0), 30, 0));
    MAJ_Panneau();
  }



  // Méthode permettant la gestion des scores
  public void GestionScore(int Niveau,int Score)
  {
	  MAJ_Panneau();
    // Parcours des meilleurs scores
    for(int i=0;i<NbJoueurs;i++)
    {
      // Si le score réalisé par le joueur est meilleurs que l'un des
      // meilleurs scores actuel
      if(Score > Attributs[i][1])
      {
        // On décalle les enregistrements contenus dans les tableaux
        // BestJoueurs et Attributs à partir de l'indice i
        DecallageJoueurs(i);
        String txtpos;
        if(i==0) txtpos = "er";
        else txtpos = "ème";
        // On demande la saisie du nom du joueur
        String reponse = JOptionPane.showInputDialog(this, "Vous avez réalisé le " + (i+1) + txtpos +" meilleur score!! Quel est votre nom ? ",
                                      "Félicitations!!!!",
                                      JOptionPane.INFORMATION_MESSAGE);

        Joueur monJoueur = new Joueur(reponse,Score,Niveau);
        INSERT_Score(monJoueur);
        MAJ_Panneau();
        break;
      }
    }
  }

  public void INSERT_Score(Joueur meilleurjoueur){
	  try
		{
		  	URL url=new URL("http://localhost:8080/Tetris/InsertScore");
			URLConnection connexion=url.openConnection();
			connexion.setDoOutput(true);
			// Récupération du flux de sortie
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			// Envoi du nom à rechercher
			fluxsortie.writeObject(meilleurjoueur);
			// Récupération du flux d'entrée
			ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());	
		}
	  catch (Exception sql)
		{
			System.out.println("erreur dans l'insertion"+sql);
		}
	}
  
  
  // Méthode permettant de décaller d'un rang les enregistrements dans les
  // tableaux BestJoueurs et Attributs à partir de l'indice id
  public void DecallageJoueurs(int id)
  {
    for(int i=NbJoueurs - 1;i>id;i--)
    {
      BestJoueurs[i] = BestJoueurs[i-1];
      Attributs[i][0] = Attributs[i-1][0];
      Attributs[i][1] = Attributs[i-1][1];
    }
  }

  // Méthode permettant la mise à jour du panneau suite à la modification
  // des meilleurs joueurs
  public void MAJ_Panneau()
  {
	  try
	    {
	      URL url = new URL("http://localhost:8080/Tetris/scoreUpdate");
	      URLConnection connexion = url.openConnection();
	      connexion.setDoOutput(true);
	      ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
	      fluxsortie.writeObject(null);
	      ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
	      resultat=(Vector<Joueur>) fluxentree.readObject();
	      
	      URL url2 = new URL("http://localhost:8080/Tetris/RecupTaille");
	      URLConnection connexion2 = url2.openConnection();
	      connexion2.setDoOutput(true);
	      ObjectOutputStream fluxsortie2 = new ObjectOutputStream(connexion2.getOutputStream());
	      fluxsortie2.writeObject(null);
	      ObjectInputStream fluxentree2 = new ObjectInputStream(connexion2.getInputStream());
	      int staille= (int) fluxentree2.readObject();
	      
	 
	       for (int i = 0; i < staille; i++)
	       {
	    	   BestJoueurs[i] = resultat.get(i).getNom();
	    	   Attributs[i][0] = resultat.get(i).getNiveau();
	    	   Attributs[i][1] = resultat.get(i).getScore();
	    	   
		       this.LabelNom[i].setText(BestJoueurs[i]);
		       this.LabelNiveau[i].setText(Integer.toString(Attributs[i][0]));
		       this.LabelScore[i].setText(Integer.toString(Attributs[i][1]));
	        }
	    }
	    catch (Exception sql)
	    {
	      System.out.println("erreur dans MAJ_panneau " + sql);
	    }
  }



  // Méthode exécutée lorsque l'utilisateur clique sur le bouton OK
  void BoutonOK_actionPerformed(ActionEvent e) {
    // Fermeture de la fenétre
    this.dispose();
  }
}

class PanneauScore_BoutonOK_actionAdapter implements java.awt.event.ActionListener {
  PanneauScore adaptee;

  PanneauScore_BoutonOK_actionAdapter(PanneauScore adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.BoutonOK_actionPerformed(e);
  }
}