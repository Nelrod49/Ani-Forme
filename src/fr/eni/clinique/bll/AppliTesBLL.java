package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelsDAO;
import fr.eni.clinique.bll.LoginManager;
public class AppliTesBLL {
	public static void main(String[] args) throws BLLException, DALException {
		
		PersonnelsDAO personneDAO = DAOFactory.getPersonnelsDAO();
	
		//Instanciation du jeu dessai
			Personnels p1 = new Personnels("M�lanie MALALANICH","12345","vet");
			Personnels p2 = new Personnels("Odette DEJEU","123456","sec");
			LoginManager lManager = new LoginManager();
			if(lManager.validerPersonnels(p1)){
				System.out.println("Connection r�ussi");
			}else{
				System.out.println("Connection �chou�");
			}
			if(lManager.validerPersonnels(p2)){
				System.out.println("Connection r�ussi");
			}else{
				System.out.println("Connection �chou�");
			}
			
			
		//test validation du nom
		
		

	}
}
