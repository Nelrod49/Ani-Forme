package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Personnels;

public class AppliTestDAL {
	
	public static void main(String[] args) throws DALException{
		//Déclaration et instanciation de la DAO
		
		PersonnelsDAO personnelDAO = DAOFactory.getPersonnelsDAO();
		
		//Instanciation du jeu dessai
		Personnels p1 = new Personnels();
		p1.setNom("Mélanie MALALANICH");
		p1.setMdp("12345");
		p1.setRole("vet");
		Personnels p2 = new Personnels();
		p2.setNom("Odette DEJEU");
		p2.setMdp("123456");
		p2.setRole("sec");
		Personnels p3 = new Personnels();
		p3.setNom("Edmond BOSAPIN");
		p3.setMdp("123456");
		p3.setRole("adm");
		
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