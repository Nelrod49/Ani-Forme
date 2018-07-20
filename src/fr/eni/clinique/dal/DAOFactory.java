package fr.eni.clinique.dal;

import fr.eni.clinique.dal.jdbc.ClientsDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.PersonnelsDAOJdbcImpl;

public class DAOFactory {

	public static PersonnelsDAO getPersonnelsDAO(){
		PersonnelsDAO dao = new PersonnelsDAOJdbcImpl();
		return dao;
	}
	
	//instanciation, affectation d'un nouveau client g�n�rique dao de type clientsDAO
	//c'est pour le li� � notre base de donn�es.
	public static ClientsDAO getClientsDAO(){
		ClientsDAO daoClients = new ClientsDAOJdbcImpl();
		return daoClients;
	}
}
