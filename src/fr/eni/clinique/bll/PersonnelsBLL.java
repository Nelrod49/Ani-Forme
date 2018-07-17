package fr.eni.clinique.bll;

import fr.eni.clinique.dal.*;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.DALException;

import java.awt.List;
import fr.eni.clinique.bo.*;




public class PersonnelsBLL {
	//Création d'une variable d'instance 
	//pour le Singleton
	private static PersonnelsBLL INSTANCE = null;
	
	private PersonnelsDAO daoPersonnels;
	
	private PersonnelsBLL() throws BLLException {
		//Obtention du DAO Personnels
		
	}
	//Singleton
	public static synchronized PersonnelsBLL getInstance() throws BLLException{	
		if (INSTANCE == null){
			INSTANCE = new PersonnelsBLL();	
		}
		return INSTANCE;
	}
	
	public void validerConnection(Personnels p) throws BLLException
	{
		boolean valide = true;
		StringBuffer sb = new StringBuffer();
		
		if(p==null){
			throw new BLLException("Personnel null");
		}
		//Les attributs du personnels sont obligatoires
		if(p.getMdp()==null || p.getMdp().trim().length()==0){
			sb.append("Le mdp  est obligatoire.\n");
			valide = false;
		}
		if(p.getNom()==null || p.getNom().trim().length()==0){
			sb.append("Le nom  est obligatoire.\n");
			valide = false;
		}
		
//		if(p.getRole()==null || p.getRole().trim().length()==0){
//			sb.append("Le role  est obligatoire.\n");
//			valide = false;
//		}
		
		// Si le personnels n'est pas valide...
		if(!valide){
			// ... lancer une exception expliquant la(les) raison(s) de l'invalidité 
			throw new BLLException(sb.toString());
		}
	}
}
