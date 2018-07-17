package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnels;
import fr.eni.clinique.dal.DALException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.PersonnelsDAO;
import fr.eni.clinique.bll.LoginManager;
public class AppliTesBLL {
	public static void main(String[] args){
		
		PersonnelsDAO personneDAO = DAOFactory.getPersonnelsDAO();
	
		//Instanciation du jeu dessai
			Personnels p1 = new Personnels("M�lanie MALALANICH","","vet");
			Personnels p2 = new Personnels("Odette DEJEU","123456","sec");
			LoginManager lManager;
			try {
				lManager = new LoginManager();
				try {
					if(lManager.validerPersonnels(p1)){
						System.out.println("Connection r�ussi");
					}else{
						System.out.println("Connection �chou�");
					}
				} catch (BLLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DALException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if(lManager.validerPersonnels(p2)){
						System.out.println("Connection r�ussi");
					}else{
						System.out.println("Connection �chou�");
					}
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (BLLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			
		//test validation du nom
		
		

	}
}
