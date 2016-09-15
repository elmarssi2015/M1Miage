package projet_tetris;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


/**
 * <p>Titre : PanneauScore</p>
 * <p>Description : Classe du panneau affichant les meilleurs scores </p>
 * <p>Copyright : Copyright (c) 2004</p>
 * <p>Soci�t� : </p>
 * @author C�dric Montillot et Djilali Maghraoui
 * @version 1.0
 */


public class PanneauScore  extends JDialog
{
  // Nombre de joueurs class�s dans le panneau des meilleurs scores
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
    // Variable utilis�e pour fixer les scores par d�faut
    int Score = 1000;
    // Initialisation du tableau contenant le nom des 5 meileurs joueurs
    BestJoueurs = new String[NbJoueurs];
    for(int i=0;i<NbJoueurs;i++)
      BestJoueurs[i] = "Computer";
    // Initialisation du tableau des attributs
    Attributs = new int[NbJoueurs][2];
    for(int i=0;i<NbJoueurs;i++)
    {
      Attributs[i][0] = 3;
      Attributs[i][1] = Score;
      Score = Score - 50;
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
  }



  // M�thode permettant la gestion des scores
  public void GestionScore(int Niveau,int Score)
  {
    // Parcours des meilleurs scores
    for(int i=0;i<NbJoueurs;i++)
    {
      // Si le score r�alis� par le joueur est meilleurs que l'un des
      // meilleurs scores actuel
      if(Score > Attributs[i][1])
      {
        // On d�calle les enregistrements contenus dans les tableaux
        // BestJoueurs et Attributs � partir de l'indice i
        DecallageJoueurs(i);
        String txtpos;
        if(i==0) txtpos = "er";
        else txtpos = "�me";
        // On demande la saisie du nom du joueur
        String reponse = JOptionPane.showInputDialog(this, "Vous avez r�alis� le " + (i+1) + txtpos +" meilleur score!! Quel est votre nom ? ",
                                      "F�licitations!!!!",
                                      JOptionPane.INFORMATION_MESSAGE);

        // On enregistre le nouveau score ainsi que le nom du joueur, et
        // le niveau atteint
        BestJoueurs[i] = reponse;
        Attributs[i][0] = Niveau;
        Attributs[i][1] = Score;
        // Mise � jour de l'affichage des meilleurs scores avec les nouvelles
        // donn�es
        MAJ_Panneau();
        // On sort de la boucle
        i=NbJoueurs;
      }
    }
  }



  // M�thode permettant de d�caller d'un rang les enregistrements dans les
  // tableaux BestJoueurs et Attributs � partir de l'indice id
  public void DecallageJoueurs(int id)
  {
    for(int i=NbJoueurs - 1;i>id;i--)
    {
      BestJoueurs[i] = BestJoueurs[i-1];
      Attributs[i][0] = Attributs[i-1][0];
      Attributs[i][1] = Attributs[i-1][1];
    }
  }



  // M�thode permettant la mise � jour du panneau suite � la modification
  // des meilleurs joueurs
  public void MAJ_Panneau()
  {
    for(int i=0;i<NbJoueurs;i++)
    {
      LabelNom[i].setText(BestJoueurs[i]);
      LabelNiveau[i].setText(Attributs[i][0]+"");
      LabelScore[i].setText(Attributs[i][1]+"");
    }
  }



  // M�thode ex�cut�e lorsque l'utilisateur clique sur le bouton OK
  void BoutonOK_actionPerformed(ActionEvent e) {
    // Fermeture de la fen�tre
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