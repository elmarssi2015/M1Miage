package projet_tetris;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ServletUpdate extends HttpServlet {

	private DataSource ds;
	Connection BD;
	Vector<Joueur> resultat = null;
	static int nbLignes=0;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try{
			// R�cup�ration du flux d'entr�e envoy� par l'applet
			ObjectInputStream entree = new ObjectInputStream(request.getInputStream());
			entree.read();
			// Pr�paration du flux de sortie
			ObjectOutputStream sortie = new ObjectOutputStream(response.getOutputStream());
			// Execution de la requ�te
			this.resultat = ExecuterRequete();
			// Envoi du r�sultat au client
			sortie.writeObject(resultat);
		}
		 catch (Exception ex){
		      System.out.println("Erreur d'ex�cution de la requ�te SQL (doPost) : " + ex);
		 }       
	}
	
	synchronized Vector<Joueur> ExecuterRequete()
	{
		try
		{
			// Ex�cution de la requ�te
			BD=ds.getConnection();
			Statement s = BD.createStatement();
			//D�claration d'un objet Joueur et d'un Vector pour stocker les 5 joueurs de la DB
			Vector<Joueur> resultat = new Vector<Joueur>();
			ResultSet r = s.executeQuery("select * from score order by score desc limit 5");
			
			while(r.next()){
				Joueur mesJoueurs = new Joueur(null, 0, 0);
				mesJoueurs.setNom(r.getString("nom")); 
				mesJoueurs.setNiveau(r.getInt("niveau"));
				mesJoueurs.setScore(r.getInt("score"));
				resultat.add(r.getRow()-1,mesJoueurs);
			}
			
			r.close();
			s.close();
			BD.close();
			s = null;
			r = null;
			return resultat;
		}
		catch (java.sql.SQLException ex) {
				System.out.println("Erreur d'ex�cution de la requ�te SQL (Update) \n"+ex);
				return null;
		}
	}
	
	
	public void init() throws ServletException{
	    try{
	      Context initCtx = new InitialContext();
	      System.out.println("lookup de env");
	      Context envCtx = (Context)initCtx.lookup("java:comp/env");
	      System.out.println("lookup de Tetris");
	      this.ds = ((DataSource)envCtx.lookup("Tetris"));
	    }
	    catch (Exception er){
	      System.out.println("Erreur de chargement du contexte (Update) " + er);
	    }
	  }
	
}