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

	/*TODO retravailler la requête pour insérer le CodeClient*/
	private static final String INSERT_ANIMAUX = "INSERT INTO Animaux (NomAnimal, Sexe, Couleur, Race, Espece, CodeClient, Tatouage, Archive) values(?,?,?,?,?,?,?,?) WHERE CodeClient;";
	static String SQL_GETANIMAUXCLIENTS_ANIMAUX = "Select * FROM Animaux Where CodeClient = ? and Archive = 0;";
	
	
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
						/*TODO voir exemple EcranPrise rendez-vous fait par Nelson*/
						prestmt.setString(4, ani.getRace());
						prestmt.setString(5, ani.getCodeClient());
						prestmt.setString(6, ani.getTatouage());
						prestmt.setString(7, ani.getArchive());
						
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
