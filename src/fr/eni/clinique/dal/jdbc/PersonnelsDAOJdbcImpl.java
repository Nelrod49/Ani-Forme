package fr.eni.clinique.dal.jdbc;

import java.sql.Statement;
import java.util.Iterator;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.PersonnelsDAO;
import fr.eni.clinique.bo.Personnels;

public class PersonnelsDAOJdbcImpl implements PersonnelsDAO{

	static String SQL_ADD_PERSONNELS = "Insert Into Personnels (Nom, MotPasse, Role, Archive) values(?,?,?,?);";
	static String SQL_DELETE_PERSONNELS = "Update Personnels archive  = true Where code = ?;";
	static String SQL_CONNECTION_PERSONNELS = "Select * from Personnels where Nom = ? AND MotPasse = ? AND Archive = 0;";
	static String SQL_GETALLDATA_PERSONNELS = "Select * from Personnels where Nom = ? AND MotPasse = ?";
	@Override
	public boolean connection(String nom, String mdp){
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
			commandeParemetree = cnx.prepareStatement(SQL_CONNECTION_PERSONNELS, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setString(1, nom);
			commandeParemetree.setString(2, mdp);
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
			boolean val = resultatDeLaRequete.next();
			if(val){
				reponse = true;
			}
		} catch (SQLException e1) {
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
	public void insert(Personnels per) throws DALException {
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
			commandeParemetree = cnx.prepareStatement(SQL_ADD_PERSONNELS, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setString(1, per.getNom());
			commandeParemetree.setString(2, per.getMdp());
			commandeParemetree.setString(3, per.getRole());
			commandeParemetree.setBoolean(4, per.getArchive());
		}catch(SQLException sqle){
			System.err.println("Impossible d'éxecuter le statement");
			sqle.printStackTrace();
		}
		try{
			commandeParemetree.executeUpdate();
		}catch(SQLException sqle){
			System.err.println("Impossible d'éxecuter la requête");
			sqle.printStackTrace();
		}
		try{
			ResultSet genKey = commandeParemetree.getGeneratedKeys();
			if(genKey.next()){
				per.setCodePersonnel(genKey.getInt(1));
			}
		}catch(SQLException e){
			System.err.println("Impossible de récupérer le code");
			e.printStackTrace();
		}
		try{
			if(cnx != null){
				cnx.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Personnels per) throws DALException {
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
			commandeParemetree = cnx.prepareStatement(SQL_DELETE_PERSONNELS, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setInt(1, per.getCodePersonnel());
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
	@Override
	public Personnels getAllData(Personnels pers){
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
			commandeParemetree = cnx.prepareStatement(SQL_GETALLDATA_PERSONNELS, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setString(1, pers.getNom());
			commandeParemetree.setString(2, pers.getMdp());
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
			if(resultatDeLaRequete.next()){
				pers.setRole(resultatDeLaRequete.getString("Role"));
				pers.setCodePersonnel(resultatDeLaRequete.getInt("CodePers"));
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
		return pers;
		
	}
	
}