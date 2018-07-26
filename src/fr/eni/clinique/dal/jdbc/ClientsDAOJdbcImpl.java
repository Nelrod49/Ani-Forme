package fr.eni.clinique.dal.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.ClientsDAO;
import fr.eni.clinique.dal.DALException;

public class ClientsDAOJdbcImpl implements ClientsDAO{
	
	/*Constantes*/
	private static String SQL_GETALLCLIENTS_CLIENTS = "Select * from Clients where Archive = 0;";
	private static final String INSERT_CLIENTS = "INSERT INTO Clients (NomClient, PrenomClient, Adresse1, Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive) values(?,?,?,?,?,?,?,?,?,?,?);";
	private static String SQL_GETALLCLIENTSPRENOMNOM_CLIENTS = "Select * from Clients where Archive = 0 AND  NomClient LIKE ? OR " + 
	" Archive = 0 AND PrenomClient LIKE ?";
	static String SQL_DELETE_CLIENT = "Update Clients SET Archive = 1 Where CodeClient = ?;";

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
						resultatDeLaRequete.getString("Remarque"));
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
	
	
	
	//Méthode d'insert d'un client 
	public void insertClient (Clients cli) throws DALException {
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
					prestmt = cnx.prepareStatement(INSERT_CLIENTS, Statement.RETURN_GENERATED_KEYS);
					prestmt.setString(1, cli.getNomClient());
					prestmt.setString(2, cli.getPrenomClient());
					prestmt.setString(3, cli.getAdresse1());
					prestmt.setString(4, cli.getAdresse2());
					prestmt.setString(5, cli.getCodePostal());
					prestmt.setString(6, cli.getVille());
					prestmt.setString(7, cli.getNumTel());
					prestmt.setString(8, cli.getAssurance());
					prestmt.setString(9, cli.getEmail());
					prestmt.setString(10, cli.getRemarque());
					prestmt.setBoolean(11, cli.getArchive());
				}catch(SQLException sqle){
					System.err.println("Impossible de préparer la requête d'insertion d'un client");
					sqle.printStackTrace();
				}
			
				//on execute la requête
				try{
					prestmt.executeUpdate();
				}catch(SQLException sqle){
					System.err.println("Impossible d'executer la requête d'insertion d'un client");
					sqle.printStackTrace();
				}
				
				//On génère une clé que l'on met dans un resultset voir ==>Statement.RETURN_GENERATED_KEYS
				//C'est pour le CodeClient qui est en AI
				try{
					ResultSet genKey = prestmt.getGeneratedKeys();
					if(genKey.next()){
						cli.setCodeClient(genKey.getInt(1));
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
	public ArrayList<Clients> allClientsNomPrenom(String nomPrenom) throws DALException {

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
			commandeParemetree = cnx.prepareStatement(SQL_GETALLCLIENTSPRENOMNOM_CLIENTS, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setString(1, '%' +  nomPrenom + '%');
			commandeParemetree.setString(2,'%' +  nomPrenom + '%');
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
						resultatDeLaRequete.getString("Remarque"));
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



	/**
	 * {@inheritedDoc}
	 * @see fr.eni.clinique.dal.ClientsDAO#getClient(java.lang.String)
	 */
	@Override
	public Clients getClient(String nomCli) throws DALException {
		Clients client = null;
		
		Connection cnx = null;
		try { 
			cnx = JdbcTools.getConnection();
		}catch (SQLException e1){
			e1.printStackTrace();
		}
		Statement stmt = null;
		ResultSet rslt = null;
		
		try{
			//Ma requete préparé
			stmt = cnx.createStatement();
			/*retourne les clés autogénéré par le statement*/
			rslt = stmt.executeQuery("SELECT nomCli FROM Clients");
			rslt.getString("nomClients");
			
			
		}catch(SQLException sqle){
			System.err.println("Impossible de préparer la requête d'insertion d'un client");
			sqle.printStackTrace();
		}
		
		return client;
	}
	
	@Override
	public void delete(int CodeClient) throws DALException {
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
			commandeParemetree = cnx.prepareStatement(SQL_DELETE_CLIENT, Statement.RETURN_GENERATED_KEYS);
			commandeParemetree.setInt(1, CodeClient);
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


