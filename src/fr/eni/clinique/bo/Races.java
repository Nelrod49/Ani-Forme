package fr.eni.clinique.bo;

public abstract class Races{

	private String race;
	private String espece;
	
	//Constructeur
	public Races(String race, String espece) {
		super();
		this.race = race;
		this.espece = espece;
	}

	
	public Races() {
		// TODO Auto-generated constructor stub
	}

	//Getter et Setter
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}

	public String getEspece() {
		return espece;
	}
	public void setEspece(String espece) {
		this.espece = espece;
	}
	
	

}
