package fr.eni.clinique.dal;

import java.util.ArrayList;

import fr.eni.clinique.bo.Clients;


public interface ClientsDAO {
	public ArrayList<Clients> allClients() throws DALException;

}
