package fr.eni.clinique.bo;

import java.util.ArrayList;
import java.util.List;

public class GestionPerso {

	private List<Personnels> listPerso = new ArrayList<>();
	Personnels perso = new Personnels();
	private String role;
	
	public void addVet(String nom, String mdp){
		this.role = "vet";
		perso = new Personnels(nom, role, mdp);
		listPerso.add(perso);
	}
	
	public Personnels getPerso(){
		return perso;
	}
	
}
