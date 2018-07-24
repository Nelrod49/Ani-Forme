package fr.eni.clinique.bo;

public class Animaux {

	private int codeAnimal;
	private String nomAnimal;
	private String sexe;
	private String couleur;
	private String race;
	private int codeClient;
	private String tatouage;
	private String antecedents;
<<<<<<< HEAD
	private boolean archive;
	
	//Constructeur
	public Animaux(int code, String race) {
=======
	private int archive;

	// Constructeur
	public Animaux(int code, Races race, String espece) {
	}

	public Animaux(int code, int race) {
>>>>>>> 3470304118a2240b65232b72433cadcfbb28fa77
		this.codeAnimal = code;
		this.race = race;
	}

	public Animaux() {
	}

<<<<<<< HEAD
	public Animaux(int code, String nomAnimal, String sexe, String couleur, String race,  int codeClient,
			String tatouage, String antecedent, boolean archive) {
=======
	public Animaux(int code, String nomAnimal, String sexe, String couleur, int race, int codeClient, String tatouage,
			String antecedent, int archive) {
>>>>>>> 3470304118a2240b65232b72433cadcfbb28fa77
		this.codeAnimal = code;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = antecedent;
		this.archive = archive;
	}

	// Getter et Setter
	public int getCodeAnimal() {
		return codeAnimal;
	}

	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}

	public String getNomAnimal() {
		return nomAnimal;
	}

	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getRace() {
		return race;
	}
<<<<<<< HEAD
	public void setRace(String race) {
=======

	public void setRace(int race) {
>>>>>>> 3470304118a2240b65232b72433cadcfbb28fa77
		this.race = race;
	}

	public int getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(int codeClient) {
		this.codeClient = codeClient;
	}

	public String getTatouage() {
		return tatouage;
	}

	public void setTatouage(String tatouage) {
		this.tatouage = tatouage;
	}

	public String getAntecedents() {
		return antecedents;
	}

	public void setAntecedents(String antecedents) {
		this.antecedents = antecedents;
	}

	public boolean getArchive() {
		return archive;
	}
<<<<<<< HEAD
	public void setArchive(boolean archive) {
=======

	public void setArchive(int archive) {
>>>>>>> 3470304118a2240b65232b72433cadcfbb28fa77
		this.archive = archive;
	}

	@Override
	public String toString() {
		return "Animaux [codeAnimal=" + codeAnimal + ", nomAnimal=" + nomAnimal + ", sexe=" + sexe + ", couleur="
				+ couleur + ", race=" + race + ", tatouage=" + tatouage + ", antecedents=" + antecedents + ", archive="
				+ archive + "]";
	}

}
