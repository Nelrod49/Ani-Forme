package fr.eni.clinique.dal;

import java.util.ArrayList;

import fr.eni.clinique.bo.Especes;

public interface EspecesDAO {

	public ArrayList<Especes> allEspeces() throws DALException;
}
