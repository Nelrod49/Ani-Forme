package fr.eni.clinique.dal;

import java.util.ArrayList;

import fr.eni.clinique.bo.Animaux;

public interface AnimauxDAO {
	public ArrayList<Animaux> getAnimauxClients(int client) throws DALException;  
}
