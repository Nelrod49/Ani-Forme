package fr.eni.clinique.dal;

import java.util.ArrayList;

import fr.eni.clinique.bo.Races;

public interface RacesDAO {

	//Méthode pour l'affichage de toutes les races pour ma comboboxRace
	public ArrayList<Races> allRaces() throws DALException;
}
