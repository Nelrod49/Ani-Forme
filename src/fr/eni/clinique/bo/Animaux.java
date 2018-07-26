package fr.eni.clinique.bo;

public class Animaux {

	private Integer codeAnimal;
	private String nomAnimal;
	private String sexe;
	private String couleur;
	private int race;
	private int codeClient;
	private String tatouage;
	private String antecedents;
	private boolean archive;
	


	// Constructeur
	public Animaux(int code, Races race, String espece) {
	}

	public Animaux(int code, int race) {
		this.codeAnimal = code;
		this.race = race;
	}

	public Animaux() {
	}

	public Animaux(int code, String nomAnimal, String sexe, String couleur, int race,  int codeClient,
			String tatouage, String antecedent, boolean archive) {
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
	public Integer getCodeAnimal() {
		return codeAnimal;
	}

	public void setCodeAnimal(Integer codeAnimal) {
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

	public int getRace() {
		return race;
	}


	public void setRace(int race) {
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

	public void setArchive(boolean archive) {

		this.archive = archive;
	}

	@Override
	public String toString() {
		return "Animaux [codeAnimal=" + codeAnimal + ", nomAnimal=" + nomAnimal + ", sexe=" + sexe + ", couleur="
				+ couleur + ", race=" + race + ", tatouage=" + tatouage + ", antecedents=" + antecedents + ", archive="
				+ archive + "]";
	}

}
