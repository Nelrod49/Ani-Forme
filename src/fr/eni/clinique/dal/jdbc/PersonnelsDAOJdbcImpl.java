package fr.eni.clinique.dal.jdbc;

import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.PersonnelsDAO;
import fr.eni.clinique.bo.Personnels;

public class PersonnelsDAOJdbcImpl implements PersonnelsDAO{

	static String SQL_ADD_PERSONNELS = "Insert Into Personnels (Nom, MotPasse, Role) values(?,?,?);";
	static String SQL_DELETE_PERSONNELS = "Update Personnels archive  = true Where code = ?;";
	
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
	
}