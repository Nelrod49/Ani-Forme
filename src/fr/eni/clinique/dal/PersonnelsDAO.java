package fr.eni.clinique.dal;

import java.util.ArrayList;

import fr.eni.clinique.bo.Personnels;

public interface PersonnelsDAO{
	
	public boolean connection(String nom, String mdp) throws DALException;
	public boolean add(String nom, String mdp, String role) throws DALException;
	public void insert(Personnels per) throws DALException;
	public Personnels getAllData(Personnels per) throws DALException;
	public void delete(Personnels per) throws DALException;
	public ArrayList<Personnels> allPersonnels() throws DALException;
	public ArrayList<Personnels> allPersonnelsVeterinaire() throws DALException;
	public boolean changeMotPasse(Personnels per)throws DALException;
}