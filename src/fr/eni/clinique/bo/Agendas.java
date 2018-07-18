package fr.eni.clinique.bo;

import java.util.List;

public class Agendas {
	
	private List<RendezVous> listRdv;
	RendezVous rdv = new RendezVous();
	Personnels veto = new Personnels();
	Clients cli = new Clients();
	Animaux animal = new Animaux();
	
	//Constructeur
	public Agendas(List<RendezVous> listRdv) {
		super();
		this.listRdv = listRdv;
	}
	public Agendas(){
		super();
	}
	
	
	public void selectCilent(int codeClient){
		cli.setCodeClient(codeClient);
		rdv.setClient(cli);
	}
	
	public void selectVeto(int codeVeto){
		veto.setCodePersonnel(codeVeto);
		rdv.setVeto(veto);
	}
	
	public void selectAnimal(int codeAnimal){
		animal.setCodeAnimal(codeAnimal);
		rdv.setAnimal(animal);
	}
	
	public void selectHeure(){
		
	}
	
	public void addRdv(Clients cli, Animaux animal){
		RendezVous rdv = new RendezVous(cli.getNomClient(), animal.getNomAnimal());
		//listRdv.add(rdv);
	}
	@Override
	public String toString() {
		return "Agendas [listRdv=" + listRdv + ", rdv=" + rdv + ", veto=" + veto + ", cli=" + cli + ", animal=" + animal
				+ "]";
	}
	
	
	
}
