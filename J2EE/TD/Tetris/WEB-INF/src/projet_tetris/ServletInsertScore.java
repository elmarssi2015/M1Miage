package projet_tetris;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ServletInsertScore extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataSource ds;
	Connection BD;
	Joueur nouveauJoueur = new Joueur(null, 0, 0);

	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

	synchronized (nouveauJoueur){
		try{	
			// R�cup�ration du flux d'entr�e envoy� par l'applet
			ObjectInputStream entree = new ObjectInputStream(request.getInputStream());
			nouveauJoueur=(Joueur)entree.readObject();
			// Pr�paration du flux de sortie
			ObjectOutputStream sortie = new ObjectOutputStream(response.getOutputStream());
			sortie.writeObject(null);
			
			// Execution de la requ�te
			BD=ds.getConnection();
			Statement s = BD.createStatement();
			//D�claration d'un objet Joueur et d'un Vector pour stocker les 5 joueurs de la DB
			System.out.println("Insertion "+nouveauJoueur.getNom()+" "+nouveauJoueur.getNiveau()+" "+nouveauJoueur.getScore());
			ResultSet r=s.executeQuery("select id_joueur, min(score) AS minscore from score group by score");
			r.next();
			int val = r.getInt("id_joueur");
			s.executeUpdate("UPDATE score SET nom='"+nouveauJoueur.getNom()+"', score='"+nouveauJoueur.getScore()+"', niveau='"+nouveauJoueur.getNiveau()+"' WHERE id_joueur="+val+"");	
			r.close();
			s.close();
			BD.close();
			s = null;
			r = null;
		}
		catch (Exception ex){
			System.out.println("Erreur d'ex�cution de la requ�te SQL (insert) : " + ex);
		}  
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
	      System.out.println("Erreur de chargement du contexte " + er);
	    }
	  }
}