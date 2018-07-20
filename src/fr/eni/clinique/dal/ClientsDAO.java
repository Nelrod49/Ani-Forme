package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Clients;

public interface ClientsDAO {
	
	//Méthode pour l'insertion d'un nouveau client
	public void insertClient(Clients cli) throws DALException;
	
	
	//public boolean ajouterClient(String NomClient, String PrenomClient, String Adress1,Integer CodePostal, String Ville) throws DALException;
	

//	public boolean insertClient(String nomClient, String prenomClient, String adresse1, String codePostal,
//			String ville);


}
