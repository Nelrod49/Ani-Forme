package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Clients;
import fr.eni.clinique.dal.ClientsDAO;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.bll.BLLException;


public class GestionClientManager {
	
	//mise en place du singleton pour faire qu’une seul instance d’une classe.
	private static GestionClientManager INSTANCE = null;
	
	private ClientsDAO daoClients;
	
	private GestionClientManager() throws BLLException
	{
		//pas de catalogue à mettre en place
		//Obtention du DAO Clients
		daoClients = DAOFactory.getClientsDAO();

	}	
	public static synchronized GestionClientManager getInstance() throws BLLException{	
			if (INSTANCE == null){
				INSTANCE = new GestionClientManager();	
			}
			return INSTANCE;
	}
	
	/*Vérification que toutes les informations sont rentrée correctement*/
	public boolean validerNouveauClient (Clients c) throws BLLException
	{
		boolean valide = false;
		StringBuffer sb = new StringBuffer();
		
		if(c == null){
			throw new BLLException("Client déjà deja existant.");
		}
		
			if (c.getNomClient() == null || c.getNomClient().trim().length() == 0) {
				sb.append("Le nom est obligatoire.\n");
				return valide;
			}
			if (c.getPrenomClient() == null || c.getPrenomClient().trim().length() == 0) {
				sb.append("Le prenom est obligatoire.\n");
				return valide;
			}
			if (c.getAdresse1() == null || c.getAdresse1().trim().length() == 0) {
				sb.append("L'adresse est obligatoire.\n");
				return valide;
			}

			if (c.getCodePostal() == null || c.getCodePostal().trim().length() == 0) {
				sb.append("Le code postal est obligatoire.\n");
				return valide;
			}
			if (c.getVille() == null || c.getVille().trim().length() == 0) {
				sb.append("La ville est obligatoire.\n");
				return valide;
			}
			if (c.getNumTel() == null || c.getNumTel().trim().length() == 0) {
				sb.append("Le numero de telephone est obligatoire.\n");
				return valide;
			}
			if (c.getAssurance() == null || c.getAssurance().trim().length() == 0) {
				sb.append("L'assurance est obligatoire.\n");
				return valide;
			}
			if (c.getEmail() == null || c.getEmail().trim().length() == 0) {
				sb.append("L'Email est obligatoire.\n");
				return valide;
			}
			
			// Si le client n'est pas valide...
//			if(!valide){
//				// ... lancer une exception expliquant la(les) raison(s) de l'invaliditer 
//				throw new BLLException(sb.toString());
//			}
			
			//ClientsDAO clientsDAO = DAOFactory.getClientsDAO();
			//valide = clientsDAO.insertClient(c);

		
		return valide;
	
	}
	
	//public void ajouterClient(String NomClient, String PrenomClient, String Adress1,Integer CodePostal, String Ville) throws DALException{
	public void ajouterClient (Clients c)throws BLLException{
		
//		if(c.getCodeClient() != null){
//			throw new BLLException("Client deja existant.");
//		}
		try {
			validerNouveauClient(c);
			daoClients.insertClient(c);
		} catch (DALException e) {
			throw new BLLException("Echec d'ajout de Client", e);
		}


	}
}
		


