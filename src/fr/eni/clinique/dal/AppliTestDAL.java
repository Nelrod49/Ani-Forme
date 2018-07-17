package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Personnels;

public class AppliTestDAL {
	
	public static void main(String[] args) throws DALException{
		//D�claration et instanciation de la DAO
		
		PersonnelsDAO personnelDAO = DAOFactory.getPersonnelsDAO();
		
		//Instanciation du jeu dessai
		Personnels p1 = new Personnels();
		p1.setNom("M�lanie MALALANICH");
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
		
		//Test de la cr�ation
		System.out.println("Cr�ation du personnels ...");
		personnelDAO.insert(p1);
		System.out.println("Personnels ajout� :" + p1.toString());
		personnelDAO.insert(p2);
		System.out.println("Personnels ajout� :" + p2.toString());
		personnelDAO.insert(p3);
		System.out.println("Personnels ajout� :" + p3.toString());
		
		//Test de la connexion
		System.out.println("Test de la connnexion ...");
		if(personnelDAO.connection(p1.getNom(),"123")){
			System.out.println("Connexion de " + p1.getNom() + " r�ussi");
		}else{
			System.out.println("Connexion de " + p1.getNom() + " �chou�");
		}
		if(personnelDAO.connection(p2.getNom(), p2.getMdp())){
			System.out.println("Connexion de " + p2.getNom() + " r�ussi");
		}else{
			System.out.println("Connexion de " + p2.getNom() + " �chou�");
		}
		if(personnelDAO.connection(p3.getNom(), p3.getMdp())){
			System.out.println("Connexion de " + p3.getNom() + " r�ussi");
		}else{
			System.out.println("Connexion de " + p3.getNom() + " �chou�");
		}
	}
}