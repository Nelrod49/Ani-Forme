package fr.eni.clinique.bo;

import java.sql.Date;
import java.util.List;

public class Agendas {
	
	private int CodeAnimal;
	private int CodeVeto;
	private Date DateRdv;
	
	
	//Constructeur
	/**
	 * Constructeur vide d'Agendas
	 */
	public Agendas(){
	}
	/**
	 * Constructeur avec les trois paramètres
	 * @param CodeVeto Code du vétérinaire
	 * @param DateRdv Date du rendez-vous
	 * @param CodeAnimal Code de l'animal
	 */
	public Agendas(int CodeVeto, Date DateRdv, int CodeAnimal){
		this.CodeVeto = CodeVeto;
		this.DateRdv = DateRdv;
		this.CodeAnimal = CodeAnimal;
	}
	
	//Getter and Setter
	public void setCodeVeto(int CodeVeto){
		this.CodeVeto = CodeVeto;
	}
	public int getCodeVeto(){
		return this.CodeVeto;
	}
	public void setDateRdv(Date DateRdv){
		this.DateRdv = DateRdv;
	}
	public Date getDateRdv(){
		return this.DateRdv;
	}
	public void setCodeAnimal(int CodeAnimal){
		this.CodeAnimal = CodeAnimal;
	}
	public int getCodeAnimal(){
		return CodeAnimal;
	}
	@Override
	public String toString() {
		return "Agendas [CodeVeto=" + CodeVeto + ", DateRdv=" + DateRdv + ", CodeAnimal=" + CodeAnimal + "]";
	}
	
	
	
}
