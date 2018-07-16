package fr.eni.clinique.dal;

import fr.eni.clinique.bo.Personnels;

public interface PersonnelsDAO{
	
	public void insert(Personnels per) throws DALException;
	
	public void delete(Personnels per) throws DALException;
}