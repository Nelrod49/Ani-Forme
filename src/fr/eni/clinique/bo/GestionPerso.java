package fr.eni.clinique.bo;

import java.util.ArrayList;
import java.util.List;

public class GestionPerso {

	private List<Personnels> listPerso = new ArrayList<>();
	Personnels perso = new Personnels();
	private String role;
	
	//Getter
		public Personnels getPerso(){
			return perso;
		}
	
	//Methodes
	public void addVet(int codePerso, String nom, String mdp){
		this.role = "vet";
		perso = new Personnels(codePerso, nom, mdp, role);
		listPerso.add(perso);
	}
	public void addSec(int codePerso, String nom, String mdp){
		this.role = "sec";
		perso = new Personnels(codePerso, nom, mdp, role);
		listPerso.add(perso);
	}
	public void addAdm(int codePerso, String nom, String mdp){
		this.role = "adm";
		perso = new Personnels(codePerso, nom, mdp, role);
		listPerso.add(perso);
	}
	
	public void updateMdp(int codePerso){
		this.getPerso().setMdp("12345");
	}
	
	public void removePerso(int codePerso){
		this.getPerso().setArchive(true);
		listPerso.remove(codePerso);
		
	}

	@Override
	public String toString() {
		return "GestionPerso [listPerso=" + listPerso + ", perso=" + perso + ", role=" + role + "]";
	}
	
}
