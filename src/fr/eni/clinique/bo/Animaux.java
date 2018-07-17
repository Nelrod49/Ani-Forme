package fr.eni.clinique.bo;

public class Animaux extends Races{
	
	
	
	private int codeAnimal;
	private String nomAnimal;
	private String sexe;
	private String couleur;
	private String race;
	private String espece;
	private String tatouage;
	private String antecedents;
	private String archive;
	
	//Constructeur
	public Animaux(String race, String espece) {
		super(race, espece);
		this.race = race;
		this.espece = espece;
	}
	
	public Animaux(){
		super();
	}

	//Getter et Setter
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
	public void setRace(String race) {
		this.race = race;
	}

	public String getEspece() {
		return espece;
	}
	public void setEspece(String espece) {
		this.espece = espece;
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

	public String getArchive() {
		return archive;
	}
	public void setArchive(String archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		return "Animaux [codeAnimal=" + codeAnimal + ", nomAnimal=" + nomAnimal + ", sexe=" + sexe + ", couleur="
				+ couleur + ", race=" + race + ", espece=" + espece + ", tatouage=" + tatouage + ", antecedents="
				+ antecedents + ", archive=" + archive + "]";
	}
	
	
}
