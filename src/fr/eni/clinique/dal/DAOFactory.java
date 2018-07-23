package fr.eni.clinique.dal;

import fr.eni.clinique.dal.jdbc.AgendasDAOJDBCImpl;
import fr.eni.clinique.dal.jdbc.AnimauxDAOJDBCImpl;
import fr.eni.clinique.dal.jdbc.ClientsDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.PersonnelsDAOJdbcImpl;

public class DAOFactory {

	public static PersonnelsDAO getPersonnelsDAO(){
		PersonnelsDAO dao = new PersonnelsDAOJdbcImpl();
		return dao;
	}
	
	//instanciation, affectation d'un nouveau client générique dao de type clientsDAO
	//c'est pour le lié à notre base de données.
	public static ClientsDAO getClientsDAO(){
		ClientsDAO daoClients = new ClientsDAOJdbcImpl();
		return daoClients;
	}
	
	public static AnimauxDAO getAnimauxDAO(){
		AnimauxDAO dao = new AnimauxDAOJDBCImpl();
		return dao;
	}
	
	public static AgendasDAO getAgendasDAO(){
		AgendasDAO dao = new AgendasDAOJDBCImpl();
		return dao;
	}
}
