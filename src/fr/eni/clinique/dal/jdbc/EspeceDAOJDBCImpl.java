package fr.eni.clinique.dal.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.clinique.bo.Especes;
import fr.eni.clinique.bo.Races;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.EspecesDAO;

public class EspeceDAOJDBCImpl implements EspecesDAO {
	
	private static final String ALL_ESPECES = "Select * from Espece;";

	
	public ArrayList<Especes> allEspeces() throws DALException{
		
		ArrayList<Especes> resultat = new ArrayList<Especes>();
		Connection cnx = null;
		boolean reponse = false;
		try {
			cnx = JdbcTools.getConnection();			
		}catch(SQLException e1){
			e1.printStackTrace();
		}
		Statement commande = null;
		PreparedStatement commandeParemetree = null;
		CallableStatement appelProcedureStockee = null;
		
		try{
			commande = cnx.createStatement();
			commandeParemetree = cnx.prepareStatement(ALL_ESPECES, Statement.RETURN_GENERATED_KEYS);
		}catch(SQLException sqle){
			System.err.println("Impossible d'éxecuter la requête pour obtenir toutes les especes");
			sqle.printStackTrace();
		}
		ResultSet resultatDeLaRequete = null;
		try{
			resultatDeLaRequete = commandeParemetree.executeQuery();
		}catch(SQLException e){
			System.err.println("Impossible d'éxecuter la requête pour obtenir toutes les especes");
			e.printStackTrace();
		}
		try {
			while(resultatDeLaRequete.next()){
				Especes espece = new Especes(resultatDeLaRequete.getInt("CodeEspece"),
						resultatDeLaRequete.getString("Espece"));
				resultat.add(espece);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			if(cnx != null){
				cnx.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return resultat;
	
	
	}
}
