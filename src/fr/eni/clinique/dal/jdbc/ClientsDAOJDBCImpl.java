package fr.eni.clinique.dal.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.ClientsDAO;
import fr.eni.clinique.dal.DALException;

public class ClientsDAOJDBCImpl implements ClientsDAO{
	
	static String SQL_GETALLCLIENTS_CLIENTS = "Select * from Clients where Archive = 0;";

	
	@Override
	public ArrayList<Clients> allClients() throws DALException {

		ArrayList<Clients> resultat = new ArrayList<Clients>();
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
			commandeParemetree = cnx.prepareStatement(SQL_GETALLCLIENTS_CLIENTS, Statement.RETURN_GENERATED_KEYS);
		}catch(SQLException sqle){
			System.err.println("Impossible d'éxecuter la requête");
			sqle.printStackTrace();
		}
		ResultSet resultatDeLaRequete = null;
		try{
			resultatDeLaRequete = commandeParemetree.executeQuery();
		}catch(SQLException e){
			System.err.println("Impossible d'éxecuter la requête");
			e.printStackTrace();
		}
		try {
			while(resultatDeLaRequete.next()){
				Clients pers = new Clients(
						resultatDeLaRequete.getInt("CodeClient"),
						resultatDeLaRequete.getString("NomClient"),
						resultatDeLaRequete.getString("PrenomClient"),
						resultatDeLaRequete.getString("Adresse1"),
						resultatDeLaRequete.getString("Adresse2"),
						resultatDeLaRequete.getString("CodePostal"),
						resultatDeLaRequete.getString("Ville"),
						resultatDeLaRequete.getString("NumTel"),
						resultatDeLaRequete.getString("Assurance"),
						resultatDeLaRequete.getString("Email"),
						resultatDeLaRequete.getString("Remarque"),
						resultatDeLaRequete.getBoolean("Archive"));
				resultat.add(pers);
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
