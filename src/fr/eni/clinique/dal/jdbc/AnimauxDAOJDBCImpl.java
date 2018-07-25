package fr.eni.clinique.dal.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.clinique.bo.Animaux;
import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.dal.AnimauxDAO;
import fr.eni.clinique.dal.DALException;

public class AnimauxDAOJDBCImpl implements AnimauxDAO{

	static String SQL_GETANIMAUXCLIENTS_ANIMAUX = "Select * FROM Animaux Where CodeClient = ? and Archive = 0;";
	static String SQL_GETANIMAUXCLIENTSRACES_ANIMAUX = "Select a.CodeAnimal, a.NomAnimal, a.Sexe, a.Couleur, e.Espece, r.Race,  a.Tatouage "+ 
	" FROM Animaux as a Inner Join Races as r On a.CodeRace = r.CodeRace " +
	" Inner Join Espece as e On r.CodeEspece = e.CodeEspece Where CodeClient = ? and Archive = 0;";
	@Override
	public ArrayList<Animaux> getAnimauxClients(int client) throws DALException {
		// TODO Auto-generated method stub
		ArrayList<Animaux> resultat = new ArrayList<Animaux>();
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
			commandeParemetree = cnx.prepareStatement(SQL_GETANIMAUXCLIENTS_ANIMAUX, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setInt(1, client);
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
				Animaux animal = new Animaux(
						resultatDeLaRequete.getInt("CodeAnimal"),
						resultatDeLaRequete.getString("NomAnimal"),
						resultatDeLaRequete.getString("Sexe"),
						resultatDeLaRequete.getString("Couleur"),
						resultatDeLaRequete.getInt("CodeRace"),
						resultatDeLaRequete.getInt("CodeClient"),
						resultatDeLaRequete.getString("Tatouage"),
						resultatDeLaRequete.getString("Antecedents"),
						resultatDeLaRequete.getInt("Archive"));
				resultat.add(animal);
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
	/**
	 * {@inheritedDoc}
	 * @see fr.eni.clinique.dal.AnimauxDAO#insertAnimaux(fr.eni.clinique.bo.Animaux)
	 */
	@Override
	public void insertAnimaux(Animaux ani) throws DALException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * {@inheritedDoc}
	 * @see fr.eni.clinique.dal.AnimauxDAO#updateAnimaux(fr.eni.clinique.bo.Animaux)
	 */
	@Override
	public void updateAnimaux(Animaux ani) throws DALException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<ArrayList> getAnimauxClientsRaces(int client) throws DALException {
		// TODO Auto-generated method stub
		ArrayList<ArrayList> resultat = new ArrayList<ArrayList>();
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
			commandeParemetree = cnx.prepareStatement(SQL_GETANIMAUXCLIENTSRACES_ANIMAUX, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setInt(1, client);
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
				data.add(resultatDeLaRequete.getString("CodeAnimal"));
				data.add(resultatDeLaRequete.getString("NomAnimal"));
				data.add(resultatDeLaRequete.getString("Sexe"));
				data.add(resultatDeLaRequete.getString("Couleur"));
				data.add(resultatDeLaRequete.getString("Espece"));
				data.add(resultatDeLaRequete.getString("Race"));
				data.add(resultatDeLaRequete.getString("Tatouage"));
				resultat.add(data);
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
