package fr.eni.clinique.dal.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.clinique.bo.Agendas;
import fr.eni.clinique.dal.AgendasDAO;
import fr.eni.clinique.dal.DALException;

public class AgendasDAOJDBCImpl implements AgendasDAO{

	static String SQL_GET_ALLAGENDASVETO =  "Select  ag.CodeVeto, ag.CodeAnimal, ag.DateRdv, c.NomClient, c.PrenomClient, anm.NomAnimal, r.Race  "
			+ " from Agendas as ag Inner Join Animaux as anm On ag.CodeAnimal = anm.CodeAnimal" +
				" Inner Join Races as r On anm.CodeRace = r.CodeRace "+
				" Inner Join Clients as c On anm.CodeClient = c.CodeClient" +
				" Where ag.CodeVeto = ? AND ag.DateRdv > sysdatetime() Order By ag.DateRdv;";

	static String SQL_DELETE_DELETEAGENDAS = "DELETE FROM Agendas Where CodeVeto = ? AND CodeAnimal = ? AND DateRdv = ?;";
	@Override
	public ArrayList<ArrayList> getAllRdvVet(int CodeVet) {
		Connection cnx = null;
		ArrayList<ArrayList> reponse = new ArrayList<ArrayList>();
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
			commandeParemetree = cnx.prepareStatement(SQL_GET_ALLAGENDASVETO, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setInt(1, CodeVet);
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
				ArrayList<String> data = new ArrayList<>();
				data.add(resultatDeLaRequete.getString("CodeVeto"));
				data.add(resultatDeLaRequete.getString("CodeAnimal"));
				data.add(resultatDeLaRequete.getString("DateRdv"));
				data.add(resultatDeLaRequete.getString("NomClient"));
				data.add(resultatDeLaRequete.getString("PrenomClient"));
				data.add(resultatDeLaRequete.getString("NomAnimal"));
				data.add(resultatDeLaRequete.getString("Race"));
				reponse.add(data);
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
		return reponse;
	}
	@Override
	public void deleteAgendas(String CodeVet, String CodeAnimal, String DateRdv) throws DALException {
		Connection cnx = null;
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
			commandeParemetree = cnx.prepareStatement(SQL_DELETE_DELETEAGENDAS, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setString(1, CodeVet);
			commandeParemetree.setString(2,CodeAnimal);
			commandeParemetree.setString(3, DateRdv);
		}catch(SQLException sqle){
			System.err.println("Impossible d'éxecuter la requête");
			sqle.printStackTrace();
		}
		try{
			commandeParemetree.executeUpdate();
		}catch(SQLException sqle){
			System.err.println("Impossible d'éxecuter la requête");
			sqle.printStackTrace();
		}

		try{
			if(cnx != null){
				cnx.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
}
