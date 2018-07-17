package fr.eni.clinique.bo;

import java.util.ArrayList;
import java.util.List;


public class AppliTestBO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Personnels> listPerso = new ArrayList<>();
		GestionPerso gp = new GestionPerso();
		Personnels perso = new Personnels();

		perso = new Personnels();
		gp.addVet(1, "Jean", "JeanMDP");
		perso = gp.getPerso();
		listPerso.add(perso);
		
		perso = new Personnels();
		gp.addAdm(2, "Admin", "AdminMDP");
		perso = gp.getPerso();
		listPerso.add(perso);
		
		perso = new Personnels();
		gp.addSec(3, "Marcel", "MarcelMDP");
		perso = gp.getPerso();
		listPerso.add(perso);
		
		
		afficherCatalogue(listPerso);
		

	}
	
	private static void afficherCatalogue(List<Personnels> listPerso) {
		for (Personnels unPerso : listPerso) {
			System.out.println(unPerso.toString());
		}
	}

}
