package fr.eni.clinique.dal;


import fr.eni.clinique.dal.jdbc.AnimauxDAOJDBCImpl;
import fr.eni.clinique.dal.jdbc.ClientsDAOJDBCImpl;
import fr.eni.clinique.dal.jdbc.PersonnelsDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.RacesDAOJDBCImpl;

public class DAOFactory {

	public static PersonnelsDAO getPersonnelsDAO(){
		PersonnelsDAO dao = new PersonnelsDAOJdbcImpl();
		return dao;
	}
	
	//instanciation, affectation d'un nouveau client g�n�rique dao de type clientsDAO
	//c'est pour le li� � notre base de donn�es.
	public static ClientsDAO getClientsDAO(){
		ClientsDAO daoClients = new ClientsDAOJDBCImpl();
		return daoClients;
	}
	
	public static AnimauxDAO getAnimauxDAO(){
		AnimauxDAO dao = new AnimauxDAOJDBCImpl();
		return dao;
	}

	public static RacesDAO getRaceDAO() {
		RacesDAO daoRaces = new RacesDAOJDBCImpl();
		return daoRaces;
	}
}
