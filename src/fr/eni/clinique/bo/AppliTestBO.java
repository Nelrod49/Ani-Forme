package fr.eni.clinique.bo;

import java.util.ArrayList;
import java.util.List;


public class AppliTestBO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Personnels> listPerso = new ArrayList<>();
		List<Agendas> listRdv = new ArrayList<>();
		GestionPerso gp = new GestionPerso();
		Personnels perso = new Personnels();
		Agendas agd = new Agendas();
		Animaux animal = new Animaux();
		Clients cli = new Clients();

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
		
		animal = new Animaux(2, "Chien", "Pitbull");
		//System.out.println(animal.getEspece());
		
		
		agd = new Agendas();
		agd.selectCilent(1);
		agd.selectVeto(1);
		agd.selectAnimal(2);
		agd.addRdv(cli, animal);
		listRdv.add(agd);
		
		
		affCataloguePerso(listPerso);
		affCatalogueRdv(listRdv);
		

	}
	
	private static void affCataloguePerso(List<Personnels> listPerso) {
		for (Personnels unPerso : listPerso) {
			System.out.println(unPerso.toString());
		}
	}
	
	private static void affCatalogueRdv(List<Agendas> listRdv) {
		for (Agendas unRdv : listRdv) {
			System.out.println(unRdv.toString());
		}
	}

}
