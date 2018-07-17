package fr.eni.clinique.bo;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class Agendas {
	
	Personnels veto = new Personnels();
	Animaux animal = new Animaux();

	private int codeVeto = veto.getCodePersonnel();
	private int codeAnimal = animal.getCodeAnimal();
	LocalDateTime currentTime = LocalDateTime.now();
	
	//Getter et Setter
	public int getCodeVeto() {
		return codeVeto;
	}
	public void setCodeVeto(int codeVeto) {
		this.codeVeto = codeVeto;
	}

	public int getCodeAnimal() {
		return codeAnimal;
	}
	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}
	
	public LocalDateTime getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(LocalDateTime currentTime) {
		this.currentTime = currentTime;
	}
	
	
}
