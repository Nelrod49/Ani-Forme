package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelsDAO;
import fr.eni.clinique.bll.LoginManager;
public class AppliTesBLL {
	public static void main(String[] args) {
		PersonnelsDAO personneDAO = DAOFactory.getPersonnelsDAO();
	
		//Instanciation du jeu dessai
			Personnels p1 = new Personnels(1,"M�lanie MALALANICH","","vet");
			Personnels p2 = new Personnels(2,"Odette DEJEU","123456","sec");
			LoginManager lManager;
			try {
				lManager = new LoginManager();
				try {
					if(lManager.validerPersonnel(p1)){
						System.out.println("Connection r�ussi");
					}else{
						System.out.println("Connection �chou�");
					}
				} catch (BLLException e1) {

					e1.printStackTrace();
				} catch (DALException e1) {

					e1.printStackTrace();
				}
				try {
					if(lManager.validerPersonnel(p2)){
						System.out.println("Connection r�ussi");
					}else{
						System.out.println("Connection �chou�");
					}
				} catch (BLLException e) {

					e.printStackTrace();
				} catch (DALException e) {

					e.printStackTrace();
				}
			} catch (BLLException e2) {

				e2.printStackTrace();
			}
			
			
			
		//test validation du nom
		
		

	}
}