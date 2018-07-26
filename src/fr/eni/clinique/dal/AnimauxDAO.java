package fr.eni.clinique.dal;

import java.util.ArrayList;

import fr.eni.clinique.bo.Animaux;
import fr.eni.clinique.bo.Clients;

public interface AnimauxDAO {
	public ArrayList<Animaux> getAnimauxClients(int client) throws DALException;  
	
	//Méthode pour l'ajou d'un animal client et la modification d'un de ses animal
	public void insertAnimaux(Animaux ani) throws DALException;
	public void updateAnimaux(Animaux ani)throws DALException;
	public ArrayList<ArrayList> getAnimauxClientsRaces(int client) throws DALException;  
	public void delete(int CodeAnimal) throws DALException;
}
