package fr.eni.clinique.bo;

import java.time.LocalDateTime;

public class RendezVous {
	
	private Personnels veto = new Personnels();
	private Animaux animal = new Animaux();
	private Clients client = new Clients();
	private LocalDateTime currentTime = LocalDateTime.now();
	
	//Constructeur
	public RendezVous(String nomClient, String nomAnimal){
		super();
	}
	
	public RendezVous(){
		super();
	}
	
	//Getter et Setter
	public Personnels getVeto() {
		return veto;
	}
	public void setVeto(Personnels veto) {
		this.veto = veto;
	}

	public Animaux getAnimal() {
		return animal;
	}
	public void setAnimal(Animaux animal) {
		this.animal = animal;
	}

	public Clients getClient() {
		return client;
	}
	public void setClient(Clients client) {
		this.client = client;
	}

	public LocalDateTime getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(LocalDateTime currentTime) {
		this.currentTime = currentTime;
	}

	
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("Rdv [");
		buf.append(" Heure=");
		buf.append(getCurrentTime());
		buf.append(", Nom du client=");
		buf.append(client.getNomClient());
		buf.append(" ");
		buf.append(client.getPrenomClient());
		buf.append(", Animal=");
		buf.append(animal.getNomAnimal());
		buf.append(", Race=");
		buf.append(animal.getRace());
		buf.append(" ]");
		return buf.toString();
	}
	
}
