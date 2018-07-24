package fr.eni.clinique.dal;

<<<<<<< HEAD

=======
import fr.eni.clinique.dal.jdbc.AgendasDAOJDBCImpl;
>>>>>>> 3470304118a2240b65232b72433cadcfbb28fa77
import fr.eni.clinique.dal.jdbc.AnimauxDAOJDBCImpl;
import fr.eni.clinique.dal.jdbc.ClientsDAOJdbcImpl;
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
		ClientsDAO daoClients = new ClientsDAOJdbcImpl();
		return daoClients;
	}
	
	public static AnimauxDAO getAnimauxDAO(){
		AnimauxDAO dao = new AnimauxDAOJDBCImpl();
		return dao;
	}
<<<<<<< HEAD

	public static RacesDAO getRaceDAO() {
		RacesDAO daoRaces = new RacesDAOJDBCImpl();
		return daoRaces;
=======
	
	public static AgendasDAO getAgendasDAO(){
		AgendasDAO dao = new AgendasDAOJDBCImpl();
		return dao;
>>>>>>> 3470304118a2240b65232b72433cadcfbb28fa77
	}
}
