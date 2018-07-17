package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Personnels;

public class AppliTestDAL {
	
	public static void main(String[] args) throws DALException{
		//Déclaration et instanciation de la DAO
		
		PersonnelsDAO personnelDAO = DAOFactory.getPersonnelsDAO();
		
		//Instanciation du jeu dessai
		Personnels p1 = new Personnels("Mélanie MALALANICH","12345","vet");
		Personnels p2 = new Personnels("Odette DEJEU","123456","sec");
		Personnels p3 = new Personnels("Edmond BOSAPIN","123456","adm");
		
		//Test de la création
		System.out.println("Création du personnels ...");
		personnelDAO.insert(p1);
		System.out.println("Personnels ajouté :" + p1.toString());
		personnelDAO.insert(p2);
		System.out.println("Personnels ajouté :" + p2.toString());
		personnelDAO.insert(p3);
		System.out.println("Personnels ajouté :" + p3.toString());
		
		//Test de la connexion
		System.out.println("Test de la connnexion ...");
		if(personnelDAO.connection(p1.getNom(),"123")){
			System.out.println("Connexion de " + p1.getNom() + " réussi");
		}else{
			System.out.println("Connexion de " + p1.getNom() + " échoué");
		}
		if(personnelDAO.connection(p2.getNom(), p2.getMdp())){
			System.out.println("Connexion de " + p2.getNom() + " réussi");
		}else{
			System.out.println("Connexion de " + p2.getNom() + " échoué");
		}
		if(personnelDAO.connection(p3.getNom(), p3.getMdp())){
			System.out.println("Connexion de " + p3.getNom() + " réussi");
		}else{
			System.out.println("Connexion de " + p3.getNom() + " échoué");
		}
	}
}