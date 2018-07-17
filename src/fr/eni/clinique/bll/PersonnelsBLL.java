package fr.eni.clinique.bll;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelsDAO;


public class PersonnelsBLL {
	//Création d'une variable d'instance 
	//pour le Singleton
	private static PersonnelsBLL INSTANCE = null;
	
	
<<<<<<< HEAD:src/fr/eni/clinique/bll/PersonnelsBLL.java
	private PersonnelsBLL() throws BLLException {
=======
	public LoginManager() throws BLLException {
>>>>>>> c9fe26de7cb587c8c55eeee35f72332802cb0115:src/fr/eni/clinique/bll/LoginManager.java
		//Obtention du DAO Personnels
		
	}
	//Singleton
	public static synchronized PersonnelsBLL getInstance() throws BLLException{	
		if (INSTANCE == null){
			INSTANCE = new PersonnelsBLL();	
		}
		return INSTANCE;
	}
	
<<<<<<< HEAD:src/fr/eni/clinique/bll/PersonnelsBLL.java
	public void validerConnection(Personnels p) throws BLLException
=======
	public boolean validerPersonnels(Personnels p) throws BLLException, DALException
>>>>>>> c9fe26de7cb587c8c55eeee35f72332802cb0115:src/fr/eni/clinique/bll/LoginManager.java
	{
		boolean valide = false;
		StringBuffer sb = new StringBuffer();
		
		if(null == p){
			throw new BLLException("Personnel null");
		}
		//Les attributs du personnels sont obligatoires
		if(null == p.getMdp() || p.getMdp().trim().length() == 0){
			sb.append("Le mdp  est obligatoire.\n");
			return valide;
		}
		if(p.getNom()==null || p.getNom().trim().length()==0){
			sb.append("Le nom  est obligatoire.\n");
			return valide;
		}
		
		PersonnelsDAO personnelsDAO = DAOFactory.getPersonnelsDAO();

		valide = personnelsDAO.connection(p.getNom(), p.getMdp());
		
		return valide;
	}
}
