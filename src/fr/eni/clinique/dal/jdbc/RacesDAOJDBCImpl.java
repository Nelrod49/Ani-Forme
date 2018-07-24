package fr.eni.clinique.dal.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.clinique.dal.RacesDAO;
import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.bo.Races;
import fr.eni.clinique.dal.DALException;

public class RacesDAOJDBCImpl implements RacesDAO {
	private static final String ALL_RACES = "Select * from Races;";

	public ArrayList<Races> allRaces() throws DALException {


		ArrayList<Races> resultat = new ArrayList<Races>();
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
			commandeParemetree = cnx.prepareStatement(ALL_RACES, Statement.RETURN_GENERATED_KEYS);
		}catch(SQLException sqle){
			System.err.println("Impossible d'éxecuter la requête pour obtenir toutes les races");
			sqle.printStackTrace();
		}
		ResultSet resultatDeLaRequete = null;
		try{
			resultatDeLaRequete = commandeParemetree.executeQuery();
		}catch(SQLException e){
			System.err.println("Impossible d'éxecuter la requête pour obtenir toutes les races");
			e.printStackTrace();
		}
		try {
			while(resultatDeLaRequete.next()){
				Races race = new Races(
						resultatDeLaRequete.getString("Race"));
				resultat.add(race);
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
