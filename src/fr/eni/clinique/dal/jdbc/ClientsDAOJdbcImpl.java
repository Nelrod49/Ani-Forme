package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.dal.ClientsDAO;
import fr.eni.clinique.dal.DALException;

public class ClientsDAOJdbcImpl implements ClientsDAO{

	/*Constantes*/
	private static final String INSERT_CLIENTS = "INSERT INTO Clients (NomClient, PrenomClient, Adresse1, CodePostal, Ville) values(?,?,?,?,?);";

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
			prestmt.setString(4, cli.getCodePostal());
			prestmt.setString(5, cli.getVille());
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

}
