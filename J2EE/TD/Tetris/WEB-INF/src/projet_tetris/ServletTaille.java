package projet_tetris;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ServletTaille extends HttpServlet {

	private DataSource ds;
	Connection BD;
	int staille;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try{
			// R�cup�ration du flux d'entr�e envoy� par l'applet
			ObjectInputStream entree = new ObjectInputStream(request.getInputStream());
			// Pr�paration du flux de sortie
			ObjectOutputStream sortie = new ObjectOutputStream(response.getOutputStream());
			// Execution de la requ�te
			this.staille = ExecuterRequete();
			// Envoi du r�sultat au client
			sortie.writeObject(staille);
		}
		 catch (Exception ex){
		      System.out.println("Erreur d'ex�cution de la requ�te SQL (doPost) : " + ex);
		 }       
	}
	
	public int ExecuterRequete()
	{
		try
		{
			// Ex�cution de la requ�te
			BD=ds.getConnection();
			Statement s = BD.createStatement();
			ResultSet r = s.executeQuery("select count(*) AS nbLignes FROM score");

			r.next();
			staille = r.getInt("nbLignes");
			r.next();
			r.close();
			s.close();
			BD.close();
			s = null;
			r = null;
			return staille;
		}
		catch (java.sql.SQLException ex) {
				System.out.println("Erreur d'ex�cution de la requ�te SQL \n"+ex);
				return 0;
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