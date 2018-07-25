package fr.eni.clinique.bo;

public class Especes {
	private int codeEspece;
	private String espece;
	
	public Especes(int codeEspece, String espece) {
		this.codeEspece = codeEspece;
		this.espece = espece;
	}
	
	public Especes(String espece){
		this.espece = espece;
	}
	
	public int getCodeEspece() {
		return codeEspece;
	}
	public void setCodeEspece(int codeEspece) {
		this.codeEspece = codeEspece;
	}
	public String getEspece() {
		return espece;
	}
	public void setEspece(String espece) {
		this.espece = espece;
	}
	
}
