package fr.eni.clinique.dal.jdbc;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.PersonnelsDAO;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.Personnels;


public class PersonnelsDAOJdbcImpl implements PersonnelsDAO {

	static String SQL_ADD_PERSONNELS = "Insert Into Personnels (Nom, MotPasse, Role, Archive) values(?,?,?,?);";
	static String SQL_DELETE_PERSONNELS = "Update Personnels SET Archive  = 1 Where CodePers = ?;";
	static String SQL_CONNECTION_PERSONNELS = "Select * from Personnels where Nom = ? AND MotPasse = ? AND Archive = 0;";
	static String SQL_GETALLDATA_PERSONNELS = "Select * from Personnels where Nom = ? AND MotPasse = ?";
	static String SQL_GETALL_PERSONNELS = "Select * from Personnels where Archive = 0;";
	static String SQL_GETALLVETERINAIRE_PERSONNELS = "Select * from Personnels where Archive = 0 and Role = 'vet';";
	static String SQL_CHANGEMOTPASSE_PERSONNELS = "Update Personnels Set MotPasse = ? Where CodePers = ?;";

	@Override
	public boolean connection(String nom, String mdp) {
		Connection cnx = null;
		boolean reponse = false;
		try {
			cnx = JdbcTools.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Statement commande = null;
		PreparedStatement commandeParemetree = null;
		CallableStatement appelProcedureStockee = null;

		try {
			commande = cnx.createStatement();
			commandeParemetree = cnx.prepareStatement(SQL_CONNECTION_PERSONNELS, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setString(1, nom);
			commandeParemetree.setString(2, mdp);
		} catch (SQLException sqle) {
			System.err.println("Impossible d'éxecuter la requête");
			sqle.printStackTrace();
		}
		ResultSet resultatDeLaRequete = null;
		try {
			resultatDeLaRequete = commandeParemetree.executeQuery();
		} catch (SQLException e) {
			System.err.println("Impossible d'éxecuter la requête");
			e.printStackTrace();
		}
		try {
			boolean val = resultatDeLaRequete.next();
			if (val) {
				reponse = true;
			}
		} catch (SQLException e1) {
		}
		try {
			if (cnx != null) {
				cnx.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reponse;

	}

	/* (non-Javadoc)
	 * @see fr.eni.clinique.dal.PersonnelsDAO#insert(fr.eni.clinique.bo.Personnels)
	 * Ajoute un personnel
	 */
	@Override
	public void insert(Personnels per) throws DALException {
		Connection cnx = null;
		try {
			cnx = JdbcTools.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Statement commande = null;
		PreparedStatement commandeParemetree = null;
		CallableStatement appelProcedureStockee = null;

		try {
			commande = cnx.createStatement();
			commandeParemetree = cnx.prepareStatement(SQL_ADD_PERSONNELS, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setString(1, per.getNom());
			commandeParemetree.setString(2, per.getMdp());
			commandeParemetree.setString(3, per.getRole());
			commandeParemetree.setBoolean(4, per.getArchive());
		} catch (SQLException sqle) {
			System.err.println("Impossible d'éxecuter le statement");
			sqle.printStackTrace();
		}
		try {
			commandeParemetree.executeUpdate();
		} catch (SQLException sqle) {
			System.err.println("Impossible d'éxecuter la requête");
			sqle.printStackTrace();
		}
		try {
			ResultSet genKey = commandeParemetree.getGeneratedKeys();
			if (genKey.next()) {
				per.setCodePersonnel(genKey.getInt(1));
			}
		} catch (SQLException e) {
			System.err.println("Impossible de récupérer le code");
			e.printStackTrace();
		}
		try {
			if (cnx != null) {
				cnx.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Personnels per) throws DALException {
		Connection cnx = null;
		try {
			cnx = JdbcTools.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Statement commande = null;
		PreparedStatement commandeParemetree = null;
		CallableStatement appelProcedureStockee = null;

		try {
			commande = cnx.createStatement();
			commandeParemetree = cnx.prepareStatement(SQL_DELETE_PERSONNELS, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setInt(1, per.getCodePersonnel());
		} catch (SQLException sqle) {
			System.err.println("Impossible d'éxecuter la requête");
			sqle.printStackTrace();
		}
		try {
			commandeParemetree.executeUpdate();
		} catch (SQLException sqle) {
			System.err.println("Impossible d'éxecuter la requête");
			sqle.printStackTrace();
		}

		try {
			if (cnx != null) {
				cnx.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Récup le rôle d'un personnels en fonction de son nom et mot de passe

	@Override
	public Personnels getAllData(Personnels pers) {
		Connection cnx = null;
		boolean reponse = false;
		try {
			cnx = JdbcTools.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Statement commande = null;
		PreparedStatement commandeParemetree = null;
		CallableStatement appelProcedureStockee = null;

		try {
			commande = cnx.createStatement();
			commandeParemetree = cnx.prepareStatement(SQL_GETALLDATA_PERSONNELS, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setString(1, pers.getNom());
			commandeParemetree.setString(2, pers.getMdp());
		} catch (SQLException sqle) {
			System.err.println("Impossible d'éxecuter la requête");
			sqle.printStackTrace();
		}
		ResultSet resultatDeLaRequete = null;
		try {
			resultatDeLaRequete = commandeParemetree.executeQuery();
		} catch (SQLException e) {
			System.err.println("Impossible d'éxecuter la requête");
			e.printStackTrace();
		}
		try {
			if (resultatDeLaRequete.next()) {
				pers.setRole(resultatDeLaRequete.getString("Role"));
				pers.setCodePersonnel(resultatDeLaRequete.getInt("CodePers"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (cnx != null) {
				cnx.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pers;

	}

	@Override
	public ArrayList<Personnels> allPersonnels(){
		ArrayList<Personnels> resultat = new ArrayList<Personnels>();
		Connection cnx = null;
		boolean reponse = false;
		try {
			cnx = JdbcTools.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Statement commande = null;
		PreparedStatement commandeParemetree = null;
		CallableStatement appelProcedureStockee = null;

		try {
			commande = cnx.createStatement();
			commandeParemetree = cnx.prepareStatement(SQL_GETALL_PERSONNELS, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException sqle) {
			System.err.println("Impossible d'éxecuter la requête");
			sqle.printStackTrace();
		}
		ResultSet resultatDeLaRequete = null;
		try {
			resultatDeLaRequete = commandeParemetree.executeQuery();
		} catch (SQLException e) {
			System.err.println("Impossible d'éxecuter la requête");
			e.printStackTrace();
		}
		try {
			while (resultatDeLaRequete.next()) {
				Personnels pers = new Personnels(resultatDeLaRequete.getInt("CodePers"),
						resultatDeLaRequete.getString("Nom"), resultatDeLaRequete.getString("MotPasse"),
						resultatDeLaRequete.getString("Role"));
				pers.setArchive(false);
				resultat.add(pers);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (cnx != null) {
				cnx.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}

	
	@Override
	public boolean changeMotPasse(Personnels per){
		boolean valide = false;
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
			commandeParemetree = cnx.prepareStatement(SQL_CHANGEMOTPASSE_PERSONNELS, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setString(1, per.getMdp());
			commandeParemetree.setInt(2, per.getCodePersonnel());
		}catch(SQLException sqle){
			System.err.println("Impossible d'éxecuter la requête");
			sqle.printStackTrace();
		}
		try{
			commandeParemetree.executeUpdate();
			valide = true;
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
		return valide;
	}
	
	@Override
	public ArrayList<Personnels> allPersonnelsVeterinaire(){
		ArrayList<Personnels> resultat = new ArrayList<Personnels>();
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
			commandeParemetree = cnx.prepareStatement(SQL_GETALLVETERINAIRE_PERSONNELS, Statement.RETURN_GENERATED_KEYS);
		}catch(SQLException sqle){
			System.err.println("Impossible d'éxecuter la requête");
			sqle.printStackTrace();
		}
		ResultSet resultatDeLaRequete = null;
		try {
			resultatDeLaRequete = commandeParemetree.executeQuery();
		} catch (SQLException e) {
			System.err.println("Impossible d'éxecuter la requête");
			e.printStackTrace();
		}
		try {
			while(resultatDeLaRequete.next()){
				Personnels pers = new Personnels(
						resultatDeLaRequete.getInt("CodePers"),
						resultatDeLaRequete.getString("Nom"),
						resultatDeLaRequete.getString("MotPasse"),
						resultatDeLaRequete.getString("Role"));
				pers.setArchive(false);
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

	@Override
	public boolean add(String nom, String mdp, String role) throws DALException {
		
		
		return false;
	}

}