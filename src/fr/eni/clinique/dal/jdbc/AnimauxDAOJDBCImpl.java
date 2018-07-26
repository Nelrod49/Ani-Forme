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
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.AnimauxDAO;
import fr.eni.clinique.dal.DALException;

public class AnimauxDAOJDBCImpl implements AnimauxDAO {

	private static final String UPDATE_ANIMALS = "Update Animals SET (NomAnimal, Sexe, Couleur, CodeRace, Espece, Tatouage);";
	/* TODO retravailler la requête pour insérer le CodeClient */
	private static final String INSERT_ANIMAUX = "INSERT INTO Animaux (NomAnimal, Sexe, Couleur, CodeRace, Espece, CodeClient, Tatouage, Archive) "
			+ "values(?,?,?,(SELECT CodeRace FROM Races WHERE CodeEspece = ?q),?,?,?,?);";
	private static String SQL_GETANIMAUXCLIENTS_ANIMAUX = "Select * FROM Animaux Where CodeClient = ? and Archive = 0;";
	
	static String SQL_GETANIMAUXCLIENTSRACES_ANIMAUX = "Select a.CodeAnimal, a.NomAnimal, a.Sexe, a.Couleur, e.Espece, r.Race,  a.Tatouage "
			+ " FROM Animaux as a Inner Join Races as r On a.CodeRace = r.CodeRace "
			+ " Inner Join Espece as e On r.CodeEspece = e.CodeEspece Where CodeClient = ? and Archive = 0;";

	static String SQL_DELETE_ANIMAUX = "Update Animaux SET Archive = 1 Where CodeAnimal = ?;";

	@Override
	public ArrayList<Animaux> getAnimauxClients(int client) throws DALException {
		// TODO Auto-generated method stub
		ArrayList<Animaux> resultat = new ArrayList<Animaux>();
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
			commandeParemetree = cnx.prepareStatement(SQL_GETANIMAUXCLIENTS_ANIMAUX, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setInt(1, client);
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
				Animaux animal = new Animaux(resultatDeLaRequete.getInt("CodeAnimal"),
						resultatDeLaRequete.getString("NomAnimal"), 
						resultatDeLaRequete.getString("Sexe"),
						resultatDeLaRequete.getString("Couleur"),
						resultatDeLaRequete.getInt("Race"),
						resultatDeLaRequete.getInt("CodeClient"),
						resultatDeLaRequete.getString("Tatouage"),
						resultatDeLaRequete.getString("Antecedents"),
						resultatDeLaRequete.getBoolean("Archive"));
				resultat.add(animal);
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

	/**
	 * Méthode d'insert d'un animal
	 */
		public void insertAnimaux(Animaux ani) throws DALException {
					/*Connection à la base de données*/
					Connection cnx = null;
					
					try { 
						cnx = JdbcTools.getConnection();
					}catch (SQLException e1){
						e1.printStackTrace();
					}
					Statement stmt = null;
					PreparedStatement prestmt = null;
					
					try{
						//Ma requete préparé
						stmt = cnx.createStatement();
						/*retourne les clés autogénéré par le statement*/
						prestmt = cnx.prepareStatement(INSERT_ANIMAUX, Statement.RETURN_GENERATED_KEYS);
						prestmt.setString(1, ani.getNomAnimal());
						prestmt.setString(2, ani.getSexe());
						prestmt.setString(3, ani.getCouleur());
						prestmt.setInt(4, ani.getRace());
						prestmt.setInt(5, ani.getCodeClient());
						prestmt.setString(6, ani.getTatouage());
						prestmt.setBoolean(7, ani.getArchive());
						
					}catch(SQLException sqle){
						System.err.println("Impossible de préparer la requête d'insertion d'un animal");
						sqle.printStackTrace();
					}
				
					//on execute la requête
					try{
						prestmt.executeUpdate();
					}catch(SQLException sqle){
						System.err.println("Impossible d'executer la requête d'insertion d'un animal");
						sqle.printStackTrace();
					}
					
					//On génère une clé que l'on met dans un resultset voir ==>Statement.RETURN_GENERATED_KEYS
					//C'est pour le CodeClient qui est en AI
					try{
						ResultSet genKey = prestmt.getGeneratedKeys();
						if(genKey.next()){
							ani.setCodeAnimal(genKey.getInt(1));
						}
					}catch (SQLException e){
						System.err.println("Impossible de récupérer la clé autogénéré");
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
	public void updateAnimaux(Animaux ani) throws DALException {
		Connection cnx = null;
		try {
			cnx = JdbcTools.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Statement stmt = null;
		PreparedStatement prestmt = null;
		CallableStatement appelProcedureStockee = null;

		try {
			stmt = cnx.createStatement();
			prestmt = cnx.prepareStatement(UPDATE_ANIMALS, Statement.RETURN_GENERATED_KEYS);
			prestmt.setString(1, ani.getNomAnimal());
			prestmt.setString(2, ani.getSexe());
			prestmt.setString(3, ani.getCouleur());
			/* TODO voir exemple EcranPrise rendez-vous fait par Nelson */
			prestmt.setInt(4, ani.getRace());
			prestmt.setString(6, ani.getTatouage());
		} catch (SQLException sqle) {
			System.err.println("Impossible d'éxecuter la requête d'update d'un animal");
			sqle.printStackTrace();
		}
		try {
			prestmt.executeUpdate();
		} catch (SQLException sqle) {
			System.err.println("Impossible d'éxecuter la requête d'update d'un animal");
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
	
	@Override
	public void delete(int CodeAnimal) throws DALException {
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
			commandeParemetree = cnx.prepareStatement(SQL_DELETE_ANIMAUX, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setInt(1, CodeAnimal);
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
}
