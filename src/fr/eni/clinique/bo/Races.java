package fr.eni.clinique.bo;

public class Races{

	private int codeRace;
	private int codeEspece;
	private String race;
	private String espece;
	
	//Constructeur
	public Races(String race, String espece) {
		super();
		this.race = race;
		this.espece = espece;
	}
	
	public Races(String race) {
		this.race = race;
	}

	

	public Races(int codeRace, String race, int codeEspece) {
		this.codeRace = codeRace;
		this.race = race;
		this.codeEspece = codeEspece;
	}

	public int getCodeRace() {
		return codeRace;
	}

	public void setCodeRace(int codeRace) {
		this.codeRace = codeRace;
	}

	public int getCodeEspece() {
		return codeEspece;
	}

	public void setCodeEspece(int codeEspece) {
		this.codeEspece = codeEspece;
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
