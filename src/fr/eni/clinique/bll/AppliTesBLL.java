package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelsDAO;

public class AppliTesBLL {
	public static void main(String[] args) {
		
		PersonnelsDAO personneDAO = DAOFactory.getPersonnelsDAO();
	
		//Instanciation du jeu dessai
			Personnels p1 = new Personnels("Jean NEYMAR","JNemar","vet");
			Personnels p2 = new Personnels("John DUFF","JDuff","sec");
		 
		
		LoginManager manager = null;
		
		try {
			manager = LoginManager.getInstance();
		} catch (BLLException e1) {
			e1.printStackTrace();
		}

		//test validation du nom
		
		

	}
}
